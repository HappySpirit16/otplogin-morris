package co.approbe.clientcredits.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import co.approbe.commons.dto.DateFilterDto;
import co.approbe.commons.dto.DispersionSummaryDto;
import co.approbe.commons.model.CashPayment;
import co.approbe.commons.model.CashPaymentSplit;
import co.approbe.commons.model.UserCredentials;

public class BackOfficeService {

	private AmazonDynamoDB db;
	private Context context;
	private DynamoDBMapper mapper;

	private static final String DB_URL = "jdbc:mysql://34.150.132.109:3306/cashcollection";
	private static final String DB_USER = "aws";
	private static final String DB_PASSWORD = "ApprobeTest1+";

	/*
	 * 
	 * public static final String DB_URL = System.getenv("DB_URL") == null ? "" :
	 * System.getenv("DB_URL"); public static final String DB_USER =
	 * System.getenv("DB_USER") == null ? "" : System.getenv("DB_USER"); public
	 * static final String DB_PASSWORD = System.getenv("DB_PASSWORD") == null ? "" :
	 * System.getenv("DB_PASSWORD");
	 * 
	 */

	public BackOfficeService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
	}

	Connection connection = null;

	public Connection getConnection(Context pContext) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create a connection to the database
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			pContext.getLogger().log(e.getLocalizedMessage());
			pContext.getLogger().log(e.getMessage());
		}

		return connection;

	}

	public void saveCashCollection(CashPayment pCashPayment, Context pContext) {
		pContext.getLogger().log("creating in cashcollection gcp");
		getConnection(pContext);
		// Prepare the SQL statement
		String sql = "INSERT INTO cashpayment ( casheer, customer, totalAmmount, credits, datehour, coreid, paymentdate,client ) VALUES ( ?, ?, ?, ?, ?, ?,?,?)";
		PreparedStatement statement = null;
		try {
			pContext.getLogger().log("start creating map in cashcollection gcp");
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(pCashPayment.getCasheer()));
			statement.setInt(2, Integer.parseInt(pCashPayment.getCustomer()));
			statement.setInt(3, pCashPayment.getTotalAmmount().intValue());
			statement.setString(4, pCashPayment.getCredits());
			statement.setString(5, pCashPayment.getDatehour());
			statement.setString(6, pCashPayment.getCoreId());
			statement.setInt(7, pCashPayment.getPaymentDate());
			statement.setString(8, pCashPayment.getClient());
			pContext.getLogger().log("end creating map in cashcollection gcp");
			// Execute the SQL statement
			statement.executeUpdate();
			pContext.getLogger().log("create in cashcollection gcp");
			// Clean up resources
			statement.close();
			connection.close();
			pContext.getLogger().log("connection closed");
		} catch (SQLException e) {
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			pContext.getLogger().log(e.getLocalizedMessage() + e.getMessage() + e.getCause());
			try {
				connection.close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

	public boolean saveCashCollectionSplit(CashPaymentSplit pCashPaymentSplit, Context pContext) {
		getConnection(pContext);
		// Prepare the SQL statement
		String sql = "INSERT INTO cashpaymentsplit (idCredit, client, casheer, paymentId, totalAmmount, customer, financier, approbe, accountFinancier, accountAproobe, datePayment, dateHourPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pContext.getLogger().log("cash collection split start statement");
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			pContext.getLogger().log("cash collection split start mapping statement");
			statement.setLong(1, Long.parseLong(pCashPaymentSplit.getIdCredit()));
			statement.setString(2, pCashPaymentSplit.getClient());
			statement.setInt(3, Integer.parseInt(pCashPaymentSplit.getCasheer()));
			statement.setString(4, pCashPaymentSplit.getPaymentId());
			statement.setLong(5, pCashPaymentSplit.getTotalAmmount());
			statement.setInt(6, Integer.parseInt(pCashPaymentSplit.getCustomer()));
			statement.setLong(7, pCashPaymentSplit.getFinancier());
			statement.setLong(8, pCashPaymentSplit.getApprobe());
			statement.setString(9, pCashPaymentSplit.getAccountFinancier());
			statement.setString(10, pCashPaymentSplit.getAccountAproobe());
			statement.setInt(11, pCashPaymentSplit.getDatePayment());
			statement.setString(12, pCashPaymentSplit.getDateHourPayment());
			pContext.getLogger().log("cash collection split end mapping statement");
			// Execute the SQL statement
			statement.executeUpdate();

			// Clean up resources
			connection.close();
		} catch (SQLException e) {
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			pContext.getLogger().log("Error: " + new Gson().toJson(pCashPaymentSplit));
			return false;
		}
		return true;
	}

	public List<CashPayment> getCasheerCashPayments(DateFilterDto pDateFilterDto, Context pContext) {
		pContext.getLogger().log(new Gson().toJson(pDateFilterDto));
		getConnection(pContext);
		String query = "SELECT * FROM cashcollection.cashpayment " + "WHERE casheer = ? " + "AND paymentdate >= ? "
				+ "AND paymentdate <= ? " + "LIMIT ?, ?";

		List<CashPayment> cashPayments = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, Integer.parseInt(pDateFilterDto.getUserId()));
			preparedStatement.setString(2, pDateFilterDto.getStartDate());
			preparedStatement.setString(3, pDateFilterDto.getEndDate());
			preparedStatement.setInt(4, pDateFilterDto.getStart());
			preparedStatement.setInt(5, pDateFilterDto.getEnd());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					CashPayment cashPayment = new CashPayment();
					cashPayment.setId(resultSet.getString("id"));
					cashPayment.setCasheer(resultSet.getString("casheer"));
					cashPayment.setCustomer(resultSet.getString("customer"));
					cashPayment.setTotalAmmount(resultSet.getDouble("totalAmmount"));
					cashPayment.setCredits(resultSet.getString("credits"));
					cashPayment.setDatehour(resultSet.getString("datehour"));
					cashPayment.setCoreId(resultSet.getString("coreId"));
					cashPayment.setPaymentDate(resultSet.getInt("paymentDate"));

					cashPayments.add(cashPayment);
				}

			}

			connection.close();
		} catch (SQLException e) {
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
		}

		return cashPayments;
	}

	public List<DateFilterDto> getTotalsCasheerCashPayments(DateFilterDto pDateFilterDto, Context pContext) {
		getConnection(pContext);

		String query = "select paymentdate, sum(totalAmmount) as ammount FROM cashcollection.cashpayment "
				+ "WHERE casheer = ? " + "AND paymentdate >= ? " + "AND paymentdate <= ? " + "group by paymentdate";

		List<DateFilterDto> wLstTotalsCasheer = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, Integer.parseInt(pDateFilterDto.getUserId()));
			preparedStatement.setString(2, pDateFilterDto.getStartDate());
			preparedStatement.setString(3, pDateFilterDto.getEndDate());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					DateFilterDto cashPayment = new DateFilterDto();
					cashPayment.setStartDate(resultSet.getString("paymentdate"));
					cashPayment.setAmmount(resultSet.getLong("ammount"));

					wLstTotalsCasheer.add(cashPayment);
				}
			}
			connection.close();
		} catch (SQLException e) {
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
		}
		if (!wLstTotalsCasheer.isEmpty()) {
			wLstTotalsCasheer.get(0).setEnd(getNumberTotalsCasheerCashPayments(pDateFilterDto, pContext));
		}

		return wLstTotalsCasheer;
	}

	public int getNumberTotalsCasheerCashPayments(DateFilterDto pDateFilterDto, Context pContext) {
		getConnection(pContext);

		int wCant = 0;

		String query = "select count(*) as end FROM cashcollection.cashpayment " + "WHERE casheer = ? "
				+ "AND paymentdate >= ? " + "AND paymentdate <= ? ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, Integer.parseInt(pDateFilterDto.getUserId()));
			preparedStatement.setString(2, pDateFilterDto.getStartDate());
			preparedStatement.setString(3, pDateFilterDto.getEndDate());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					wCant = resultSet.getInt("end");

				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wCant;

	}

	public List<DispersionSummaryDto> getPendingClientDispersions(CashPaymentSplit pCashPaymentSplit,
			Context pContext) {

		List<DispersionSummaryDto> wLstTotalsCasheer = new ArrayList<>();

		getConnection(pContext);

		String query = "SELECT accountAproobe, SUM(approbe) as approbe FROM cashcollection.cashpaymentsplit  WHERE client =?"
				+ "AND cashpaymentsplit.approbe > cashpaymentsplit.approbeDispersion"
				+ " group by cashpaymentsplit.accountAproobe ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, pCashPaymentSplit.getClient());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					DispersionSummaryDto wDateFilterDto = new DispersionSummaryDto(
							resultSet.getString("accountAproobe"), resultSet.getLong("approbe"));
					wLstTotalsCasheer.add(wDateFilterDto);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String query2 = "SELECT accountFinancier, SUM(financier) as financier FROM cashcollection.cashpaymentsplit  WHERE client = ?"
				+ " AND cashpaymentsplit.financier > cashpaymentsplit.financerDispersion "
				+ " group by cashpaymentsplit.accountFinancier ";

		pContext.getLogger().log(query2);
		try (PreparedStatement preparedStatement = connection.prepareStatement(query2)) {
			preparedStatement.setString(1, pCashPaymentSplit.getClient());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					DispersionSummaryDto wDateFilterDto = new DispersionSummaryDto(
							resultSet.getString("accountFinancier"), resultSet.getLong("financier"));
					wLstTotalsCasheer.add(wDateFilterDto);
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
		}

		return wLstTotalsCasheer;

	}

	public List<CashPayment> getClientCashPayments(DateFilterDto pDateFilterDto, Context pContext) {
		pContext.getLogger().log(new Gson().toJson(pDateFilterDto));
		UserCredentials wCred = new UserCredentialsService(db, context).getUserCredentials(pDateFilterDto.getUserId(),
				context);
		getConnection(pContext);
		List<CashPayment> cashPayments = new ArrayList<>();

		if (pDateFilterDto.getCasheer() == null) {

			String query = "SELECT * FROM cashcollection.cashpayment " + "WHERE client = ? " + "AND paymentdate >= ? "
					+ "AND paymentdate <= ? " + "LIMIT ?, ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, wCred.getClient());
				preparedStatement.setString(2, pDateFilterDto.getStartDate());
				preparedStatement.setString(3, pDateFilterDto.getEndDate());
				preparedStatement.setInt(4, pDateFilterDto.getStart());
				preparedStatement.setInt(5, pDateFilterDto.getEnd());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						CashPayment cashPayment = new CashPayment();
						cashPayment.setId(resultSet.getString("id"));
						cashPayment.setCasheer(resultSet.getString("casheer"));
						cashPayment.setCustomer(resultSet.getString("customer"));
						cashPayment.setTotalAmmount(resultSet.getDouble("totalAmmount"));
						cashPayment.setCredits(resultSet.getString("credits"));
						cashPayment.setDatehour(resultSet.getString("datehour"));
						cashPayment.setCoreId(resultSet.getString("coreId"));
						cashPayment.setPaymentDate(resultSet.getInt("paymentDate"));
						cashPayments.add(cashPayment);
					}

				}

				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			}
		} else {
			String query = "SELECT * FROM cashcollection.cashpayment " + "WHERE client = ? " + "AND casheer = ? "
					+ "AND paymentdate >= ? " + "AND paymentdate <= ? " + "LIMIT ?, ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, wCred.getClient());
				preparedStatement.setString(2, pDateFilterDto.getCasheer());
				preparedStatement.setString(3, pDateFilterDto.getStartDate());
				preparedStatement.setString(4, pDateFilterDto.getEndDate());
				preparedStatement.setInt(5, pDateFilterDto.getStart());
				preparedStatement.setInt(6, pDateFilterDto.getEnd());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						CashPayment cashPayment = new CashPayment();
						cashPayment.setId(resultSet.getString("id"));
						cashPayment.setCasheer(resultSet.getString("casheer"));
						cashPayment.setCustomer(resultSet.getString("customer"));
						cashPayment.setTotalAmmount(resultSet.getDouble("totalAmmount"));
						cashPayment.setCredits(resultSet.getString("credits"));
						cashPayment.setDatehour(resultSet.getString("datehour"));
						cashPayment.setCoreId(resultSet.getString("coreId"));
						cashPayment.setPaymentDate(resultSet.getInt("paymentDate"));
						cashPayments.add(cashPayment);
					}

				}

				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			}
		}

		return cashPayments;
	}

	public DateFilterDto getTotalsClientCashPayments(DateFilterDto pDateFilterDto, Context pContext) {
		getConnection(pContext);
		UserCredentials wCred = new UserCredentialsService(db, context).getUserCredentials(pDateFilterDto.getUserId(),
				context);

		DateFilterDto wDateFilterDto = new DateFilterDto();

		if (pDateFilterDto.getCasheer() == null) {
			String query = "select count(*) as end, SUM(totalAmmount) AS ammount FROM cashcollection.cashpayment WHERE client = ? AND paymentdate >= ? AND paymentdate <= ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, wCred.getClient());
				preparedStatement.setString(2, pDateFilterDto.getStartDate());
				preparedStatement.setString(3, pDateFilterDto.getEndDate());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						wDateFilterDto.setEnd(resultSet.getInt("end"));
						wDateFilterDto.setAmmount(resultSet.getLong("ammount"));
					}
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			}
		} else {
			String query = "select count(*) as end, SUM(totalAmmount) AS ammount FROM cashcollection.cashpayment WHERE client = ? and casheer =? AND paymentdate >= ? AND paymentdate <= ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, wCred.getClient());
				preparedStatement.setInt(2, Integer.parseInt(pDateFilterDto.getCasheer()));
				preparedStatement.setString(3, pDateFilterDto.getStartDate());
				preparedStatement.setString(4, pDateFilterDto.getEndDate());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						wDateFilterDto.setEnd(resultSet.getInt("end"));
						wDateFilterDto.setAmmount(resultSet.getLong("ammount"));
					}
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				pContext.getLogger().log(Arrays.toString(e.getStackTrace()));
			}
		}

		return wDateFilterDto;
	}

	public void setDispersion(CashPaymentSplit pCashPaymentSplit) {

		// obtener los split de pagos del cliente y de la cuenta del financiador enviada

		//

	}

}
