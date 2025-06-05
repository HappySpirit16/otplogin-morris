package co.approbe.powwi.transaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import co.approbe.powwi.transaction.dao.BasicInformation;
import co.approbe.powwi.transaction.dao.BorrowerForm;
import co.approbe.powwi.transaction.dao.CreditLine;
import co.approbe.powwi.transaction.dao.Data;
import co.approbe.powwi.transaction.dao.LogTx;
import co.approbe.powwi.transaction.dao.RelationPay;
import co.approbe.powwi.transaction.dao.TokenPowwi;
import co.approbe.powwi.transaction.dto.CancelarRetiro;
import co.approbe.powwi.transaction.dto.DebitarDeposito;
import co.approbe.powwi.transaction.dto.Dispersar;
import co.approbe.powwi.transaction.dto.DispersarOtra;
import co.approbe.powwi.transaction.dto.PagarFactura;
import co.approbe.powwi.transaction.dto.Relation;
import co.approbe.powwi.transaction.dto.Request;
import co.approbe.powwi.transaction.dto.ResponseDispersar;
import co.approbe.powwi.transaction.dto.ResultadoDispersar;
import co.approbe.powwi.transaction.dto.ResultadoRetirarDinero;
import co.approbe.powwi.transaction.dto.RetirarDinero;
import co.approbe.powwi.transaction.dto.SolicitarExtracto;
import co.approbe.powwi.transaction.dto.TransferenciaOtraEntidad;
import co.approbe.powwi.transaction.dto.TransferenciaPowwi;
import co.approbe.powwi.transaction.dto.TransferirDeposito;

public class LambdaFunctionHandler implements RequestHandler<Request, String> {
	static Logger logger = LoggerFactory.getLogger(LambdaFunctionHandler.class);
	AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDBMapper mapper = new DynamoDBMapper(db);
	DynamoDB dynamoDB = new DynamoDB(db);
	private ConnectionFactory factory;
	ObjectMapper objectMapper = new ObjectMapper();
	private Connection connection;
	private Channel channel;

	@Override
	public String handleRequest(Request input, Context context) {

		BorrowerForm basicI = new BorrowerForm();
		Map<String, Object> values = new HashMap<>();
		Map<String, String> result = new HashMap<>();
		Integer n = input.getTransferenciaPowwi() == null ? 1 : input.getTransferenciaPowwi().length;
		Integer nO = input.getTransferenciaOtraEntidad() == null ? 1 : input.getTransferenciaOtraEntidad().length;
		TransferenciaPowwi[] transferenciaPowwi = new TransferenciaPowwi[n + 1];
		TransferenciaOtraEntidad[] transferenciaOtraEntidad = new TransferenciaOtraEntidad[nO];
		TransferenciaPowwi transferenciaPowwi1[] = new TransferenciaPowwi[1];
		Dispersar dispersar = new Dispersar();
		DispersarOtra dispersarOtra = new DispersarOtra();
		Data data = new Data();
		double approveValue = 0d;
		double oCommission = 0d;
		double fgaCommission = 0d;
		double approbeDispersar = 0d;
		double clientReceive = 0d;
		TokenPowwi tokenPowwi = new TokenPowwi();
		LogTx logTx = new LogTx();
		tokenPowwi = mapper.load(TokenPowwi.class, 1);
		BasicInformation basicInformation = new BasicInformation();
		basicInformation = mapper.load(BasicInformation.class, input.getIdAutenticacionAliado());
		String HOST = Env.AUTH_HOST;// "prep.powwi.co";
		String region = Env.AUTH_REGION; // "us-east-1";
		String cuentaApprobe = Env.AUTH_CUENTA;
		String idTransaccion = Env.AUTH_IDCUENTA;
		System.out.println("prueba" + region);
		String path = "";
		String requestPayload = "";
		TreeMap<String, String> headers = new TreeMap<String, String>();
		AWSV4Auth awsv4Auth;
		Map<String, String> header;
		String requestBody = "";
		String print = "";
		byte[] kSecret;
		try {
			kSecret = ("Huella").getBytes("UTF-8");
			byte[] print256 = hmacSha256(kSecret, "");
			print = bytesToHex(print256);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		switch (input.getOperacion()) {
		case "status":
			return "ok";
		case "acreditarDeposito":
			AcreditarDeposito acreditarDeposito = new AcreditarDeposito();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			acreditarDeposito.setTokenConvenio(tokenPowwi.getToken());
			acreditarDeposito.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			acreditarDeposito.setNumeroCelular("(+57)" + (data.getCellphone() == null ? "" : data.getCellphone()));
			acreditarDeposito.setIdTransaccionAliado(input.getIdTransaccionAliado());
			acreditarDeposito.setIdAliado(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			acreditarDeposito.setMonto(input.getMonto());
			acreditarDeposito.setReferencia1(input.getReferencia1());
			acreditarDeposito.setReferencia2(input.getReferencia2());
			acreditarDeposito.setSO(input.getSO());
			acreditarDeposito.setNombreDispositivo(input.getNombreDispositivo());
			acreditarDeposito.setIP(input.getIP());
			requestBody = acreditarDeposito.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/ade/acreditardeposito";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "solicitarExtracto":
			SolicitarExtracto solicitarExtracto = new SolicitarExtracto();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			solicitarExtracto.setTokenConvenio(tokenPowwi.getToken());
			solicitarExtracto.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			solicitarExtracto.setTokenConsulta(
					basicInformation.getTokenConsulta() == null ? "" : basicInformation.getTokenConsulta());
			solicitarExtracto
					.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			solicitarExtracto.setMes(input.getMes());
			solicitarExtracto.setAno(input.getAnio());
			requestBody = solicitarExtracto.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/ade/acreditardeposito";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "pagarFactura":
			PagarFactura pagarFactura = new PagarFactura();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			pagarFactura.setTokenConvenio(tokenPowwi.getToken());
			pagarFactura.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			pagarFactura.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			pagarFactura.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			pagarFactura.setProductId(input.getProductId());
			pagarFactura.setIdTransaccionAliado(input.getIdTransaccionAliado());
			pagarFactura.setTipoPago(input.getTipoPago());
			pagarFactura.setCodigoBarras(input.getCodigoBarras());
			pagarFactura.setReferenciaManual(input.getReferenciaManual());
			pagarFactura.setValor(input.getValor());
			pagarFactura.setSO(input.getSO());
			pagarFactura.setInfoDispositivo(input.getNombreDispositivo());
			pagarFactura.setIP(input.getIP());
			requestBody = pagarFactura.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/pfa/pagarfactura";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "debitarDeposito":
			DebitarDeposito debitarDeposito = new DebitarDeposito();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			debitarDeposito.setTokenConvenio(tokenPowwi.getToken());
			debitarDeposito.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			debitarDeposito
					.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			debitarDeposito.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			debitarDeposito.setNumeroCelular("(+57)" + (data.getCellphone() == null ? "" : data.getCellphone()));
			debitarDeposito.setIdTransaccionAliado(input.getIdTransaccionAliado());
			debitarDeposito.setMotivo(input.getMotivo());
			debitarDeposito.setMonto(input.getMonto());
			debitarDeposito.setSO(input.getSO());
			debitarDeposito.setNombreDispositivo(input.getNombreDispositivo());
			debitarDeposito.setIP(input.getIP());
			requestBody = debitarDeposito.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/dde/debitardeposito";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "transferirDeposito":
			TransferirDeposito transferirDeposito = new TransferirDeposito();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			transferirDeposito.setTokenConvenio(tokenPowwi.getToken());
			transferirDeposito
					.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			transferirDeposito
					.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			transferirDeposito.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			transferirDeposito.setNumeroCelular(data.getCellphone() == null ? "" : data.getCellphone());
			transferirDeposito.setIdTransaccionAliado(input.getIdTransaccionAliado());
			transferirDeposito.setMotivo(input.getMotivo());
			transferirDeposito.setMonto(input.getMonto());
			transferirDeposito.setSO(input.getSO());
			transferirDeposito.setNombreDispositivo(input.getNombreDispositivo());
			transferirDeposito.setIP(input.getIP());
			requestBody = transferirDeposito.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/tde/transferirdeposito";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			// Put your Access Key in place of <ACCESS_KEY> and Secret Key in place of
			// <SECRET_KEY> in double quotes
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "retirarDinero":
			RetirarDinero retirarDinero = new RetirarDinero();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			retirarDinero.setTokenConvenio(tokenPowwi.getToken());
			retirarDinero.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			retirarDinero
					.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			retirarDinero.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			retirarDinero.setNumeroCelular("(+57)" + (data.getCellphone() == null ? "" : data.getCellphone()));
			retirarDinero.setIdTransaccionAliado(input.getIdAutenticacionAliado());
			retirarDinero.setCanalRetiro(input.getCanalRetiro());
			retirarDinero.setMonto(input.getMonto());
			retirarDinero.setSO(input.getSO());
			retirarDinero.setNombreDispositivo(input.getNombreDispositivo());
			retirarDinero.setIP(input.getIP());
			requestBody = retirarDinero.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/rdi/retirardinero";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				ResultadoRetirarDinero resultadoRetiro = new ResultadoRetirarDinero();
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				resultadoRetiro = new Gson().fromJson(answerd, ResultadoRetirarDinero.class);// Revisar respuesta
				basicInformation.setIdOperacion(resultadoRetiro.getIdOperacion());
				mapper.save(basicInformation);
				String[] responseTransaction = answerd.split(",");
				if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"RDI_200\"")) {
					ResultadoRetirarDinero responseTx = new ResultadoRetirarDinero();
					try {
						String convertedTx = "";
						responseTx = new Gson().fromJson(answerd, ResultadoRetirarDinero.class);// Revisar respuesta

						logTx.setAmountTx(responseTx.getValorTotalTransaccion());
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
					} catch (Exception e) {
						logTx.setAmountTx("");
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
						return answerd;
					}
				} else {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("transaccion no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				}
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.info(e.getMessage());
			}
			return null;
		case "cancelarRetiro":
			CancelarRetiro cancelarRetiro = new CancelarRetiro();
			data = mapper.load(Data.class, input.getIdAutenticacionAliado());
			cancelarRetiro.setTokenConvenio(tokenPowwi.getToken());
			cancelarRetiro.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			cancelarRetiro
					.setIdOperacion(basicInformation.getIdOperacion() == null ? "" : basicInformation.getIdOperacion());
			cancelarRetiro.setSO(input.getSO());
			cancelarRetiro.setNombreDispositivo(input.getNombreDispositivo());
			cancelarRetiro.setIP(input.getIP());
			requestBody = cancelarRetiro.toString();
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/ctt/cancelarretirodinero";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "dispersarPagar":
			transferenciaPowwi[0] = input.getTransferenciaPowwi()[0];
			approveValue = Double.parseDouble(transferenciaPowwi[0].getMonto());
			CreditLine creditLine = mapper.load(CreditLine.class, 1);
			oCommission = approveValue * (creditLine.getCommission() / 10000d);
			oCommission = (oCommission * (1 + (creditLine.getTax() / 10000)));
			fgaCommission = approveValue * (creditLine.getCommissionFGA() / 10000d);
			fgaCommission = (fgaCommission * (1 + (creditLine.getTax() / 10000)));
			clientReceive = approveValue - fgaCommission;
			approbeDispersar = oCommission + fgaCommission;
			transferenciaPowwi[0] = new TransferenciaPowwi(input.getTransferenciaPowwi()[0].getIdTransaccionAliado(),
					input.getTransferenciaPowwi()[0].getNumeroDeposito(), "" + clientReceive,
					input.getTransferenciaPowwi()[0].getReferencia1(),
					input.getTransferenciaPowwi()[0].getReferencia2());

			basicInformation = mapper.load(BasicInformation.class, input.getIdAutenticacionAliado());

			transferenciaPowwi[1] = new TransferenciaPowwi(idTransaccion, cuentaApprobe, "" + approbeDispersar,
					input.getTransferenciaPowwi()[0].getReferencia1(),
					input.getTransferenciaPowwi()[0].getReferencia2());
			dispersar.setTokenConvenio(tokenPowwi.getToken());
			dispersar.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			dispersar.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			dispersar.setTransferenciaPowwi(transferenciaPowwi);
			dispersar.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			dispersar.setTipoDispersion(input.getTipoDispersion());
			dispersar.setSO(input.getSO());
			dispersar.setNombreDispositivo(input.getNombreDispositivo());
			dispersar.setIP(input.getIP());
			requestBody = dispersar.toString();
			requestBody = requestBody.replace("\"{", "{");
			requestBody = requestBody.replace("}\"", "}");
			requestBody = requestBody.replace("\"[", "[");
			requestBody = requestBody.replace("]\"", "]");
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/tba/dispersar";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				basicInformation.setRespuestaDispersion(answerd);
				context.getLogger().log("clientReceive" + clientReceive);
				context.getLogger().log("approbeDispersar" + approbeDispersar);
				context.getLogger().log("clientReceive" + transferenciaPowwi[0].getMonto());
				context.getLogger().log("approbeDispersar" + transferenciaPowwi[1].getMonto());

				context.getLogger().log("vector" + dispersar.getTransferenciaPowwi());
				mapper.save(basicInformation);
				ObjectMapper objectMapperSaveRelation = new ObjectMapper();
				String[] responseTransaction = answerd.split(",");
				if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_567\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Dispersión no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_502\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Huella del dispositivo invalida");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_509\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Token transaccional inválido");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if ((Arrays.asList(responseTransaction).contains("\"mensaje\": \"Transacción exitosa\"")
						|| answerd.contains("exitosa")) && !(answerd.contains("no realizada"))) {//
					logger.info("consume servicio de core");
					Map<String, String> saveRelation = new HashMap<String, String>();
					Map<String, String> saveRelation1 = new HashMap<String, String>();
					Map<String, String> saveRelation2 = new HashMap<String, String>();
					Map<String, String> saveRelation3 = new HashMap<String, String>();
					ResultadoDispersar responseTx = new ResultadoDispersar();
					responseTx = new Gson().fromJson(answerd, ResultadoDispersar.class);// Revisar respuesta
					BorrowerForm borrowerUpdate = new BorrowerForm();
					String convertedTx = "";
					for (int i = 0; i < responseTx.getTransacciones().length; i++) {
						logger.info(responseTx.getTransacciones()[i].toString());
						convertedTx = (" + " + responseTx.getTransacciones()[i].getValorTotalTransaccion())
								+ convertedTx;
					}
					saveRelation.put("httpMethod", "Desembolso");
					saveRelation.put("id", input.getIdRelationPay());
					saveRelation.put("channel", "P2P");
					saveRelation.put("profit", creditLine.getRate());
					saveRelation.put("gainApprobe", " ");
					saveRelation.put("coverageFGAValue", fgaCommission + "");
					saveRelation.put("coverageFGAPercentage", (creditLine.getCommissionFGA() / 10000d) + "");
					saveRelation.put("value", convertedTx + "");
					String bodySaveRelation = "";
					try {
						bodySaveRelation = objectMapperSaveRelation.writeValueAsString(saveRelation);
					} catch (JsonProcessingException e3) {
						logger.error(e3.getMessage());
					}
					AWSLambda lambdaClient = AWSLambdaClientBuilder.defaultClient();
					InvokeRequest invokeRequest = new InvokeRequest().withFunctionName("Proccess")
							.withPayload(bodySaveRelation);
					InvokeResult invokeResult = lambdaClient.invoke(invokeRequest);
					String resultInvo = new String(invokeResult.getPayload().array());
					resultInvo = resultInvo.replace("[", "");
					resultInvo = resultInvo.replace("]", "");
					logger.info(resultInvo);
					LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
					DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("ddMMyyyy");
					DateTimeFormatter dtformat0 = DateTimeFormatter.ofPattern("ddMMyyyyHH");
					DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					DateTimeFormatter dtformat2 = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH:mm:ss");
					String date = dateTime.format(dtformat);
					String date0 = dateTime.format(dtformat0);
					String date1 = dateTime.format(dtformat1);
					String date2 = dateTime.format(dtformat2);
					RelationPay relationPay = mapper.load(RelationPay.class, input.getIdRelationPay());
					int m = relationPay.getNumberIdBorrower().length();
					String id = relationPay.getNumberIdMoneylender() + date
							+ relationPay.getNumberIdBorrower().substring(m - 4);
					borrowerUpdate = mapper.load(BorrowerForm.class, relationPay.getNumberIdBorrower());
					saveRelation1.put("id", input.getIdRelationPay());
					saveRelation1.put("channel", "P2P");
					saveRelation1.put("profit", creditLine.getRate());
					saveRelation1.put("gainApprobe", " ");
					saveRelation1.put("coverageFGAValue", fgaCommission + "");
					saveRelation1.put("coverageFGAPercentage", (creditLine.getCommissionFGA() / 10000d) + "");
					saveRelation1.put("id", id);
					saveRelation1.put("warranty", relationPay.getId());
					saveRelation1.put("numberIdBorrower", (relationPay.getNumberIdBorrower()));
					saveRelation1.put("numberIdMoneylender", (relationPay.getNumberIdMoneylender()));
					saveRelation1.put("fechaAprobacion", (borrowerUpdate.getDateSaveBD()));
					saveRelation1.put("lastPaymentDate", (" "));
					saveRelation1.put("nameBorrower", borrowerUpdate.getNames() + borrowerUpdate.getLastName());
					saveRelation1.put("ranking", borrowerUpdate.getRanking() + "");
					saveRelation1.put("active", borrowerUpdate.getActive() + "");
					saveRelation1.put("gender", borrowerUpdate.getGender());
					saveRelation1.put("activityBorrower",
							borrowerUpdate.getCreditInformation().getProfession().getProfession());
					saveRelation1.put("plannerPay", " ");
					saveRelation1.put("saldoCredito", "0");
					saveRelation1.put("approveValue", borrowerUpdate.getApproveValue() + "");
					saveRelation1.put("rateApprobe", borrowerUpdate.getRateApprobe() + "");
					saveRelation1.put("feesApprobe", borrowerUpdate.getFeesApprobe() + "");
					saveRelation1.put("gainAprrobe", "0.0");// "pedirgananciadesdefront"
					saveRelation1.put("statusCredit", "1");
					saveRelation1.put("feesPaid", borrowerUpdate.getFeePlan() + "");
					saveRelation1.put("nextFeesDate", " ");
					saveRelation1.put("nextPaid", "" + clientReceive);
					saveRelation1.put("rentabilidad", creditLine.getRate());// pedir rentabilidad front
					saveRelation1.put("balancePaid", "0");
					saveRelation1.put("customerId", relationPay.getNumberIdBorrower() + date);
					saveRelation1.put("productNumber", id);
					saveRelation1.put("channel", "P2P");
					saveRelation1.put("creditType",
							borrowerUpdate.getCreditInformation().getPurpose().getDescription());
					saveRelation1.put("productType", " ");
					saveRelation1.put("datePayment", " ");
					saveRelation1.put("ammountAprove", borrowerUpdate.getApproveValue() + "");
					saveRelation1.put("value", borrowerUpdate.getV() + "");
					saveRelation1.put("ratePost", borrowerUpdate.getRateApprobe() + "");
					saveRelation1.put("rateInvoice", " ");
					saveRelation1.put("feesAgree", " ");
					saveRelation1.put("feesRegularity", " ");
					saveRelation1.put("feesFirstDate", " ");
					saveRelation1.put("feesLastDate", " ");
					saveRelation1.put("paidLastDate", " ");
					saveRelation1.put("ammountNextDate", " ");
					saveRelation1.put("balance", " ");
					saveRelation1.put("chargeOValue", oCommission + "");
					saveRelation1.put("chargeOPercentage", (creditLine.getCommission() / 10000d) + "");
					saveRelation1.put("causedInterest", " ");
					saveRelation1.put("paidInterest", " ");
					saveRelation1.put("defaultInterest", " ");
					saveRelation1.put("causedDefaultInterest", " ");
					saveRelation1.put("costCashing", " ");
					saveRelation1.put("causedCommission", " ");
					saveRelation1.put("paidCommission", " ");
					saveRelation1.put("causedInsurance", " ");
					saveRelation1.put("paidInsurance", " ");

					String bodySaveRelation1 = "";
					try {
						bodySaveRelation1 = objectMapperSaveRelation.writeValueAsString(saveRelation1);
					} catch (JsonProcessingException e3) {
						logger.error(e3.getMessage());
					}
					AWSLambda lambdaClient1 = AWSLambdaClientBuilder.defaultClient();
					InvokeRequest invokeRequest1 = new InvokeRequest().withFunctionName("PaymentCore")
							.withPayload(bodySaveRelation1);
					InvokeResult invokeResult1 = lambdaClient1.invoke(invokeRequest1);
					String resultInvo1 = new String(invokeResult1.getPayload().array());
					resultInvo1 = resultInvo1.replace("[", "");
					resultInvo1 = resultInvo1.replace("]", "");
					logger.info(resultInvo1);
					String fullName = borrowerUpdate.getNames();
					String[] names = fullName.split(" ");
					int sizeNames = names.length;
					if (sizeNames == 2) {
						saveRelation2.put("name1", names[0]);
						saveRelation2.put("name2", names[1]);
					} else {
						saveRelation2.put("name1", names[0]);
						saveRelation2.put("name2", " ");
					}
					String fullLastName = borrowerUpdate.getLastName();
					String[] lastName = fullLastName.split(" ");
					int sizeLastName = lastName.length;
					if (sizeLastName == 2) {
						saveRelation2.put("lastname1", lastName[0]);
						saveRelation2.put("lastname2", lastName[1]);
					} else {
						saveRelation2.put("lastname1", lastName[0]);
						saveRelation2.put("lastname2", " ");
					}
					saveRelation2.put("clientNumber", relationPay.getNumberIdBorrower() + date);
					saveRelation2.put("numberId", relationPay.getNumberIdBorrower());
					saveRelation2.put("typeId", borrowerUpdate.getIdentification().getDescription());
					saveRelation2.put("bussinesName",
							borrowerUpdate.getCreditInformation().getLaborInformation().getCompanyName());
					saveRelation2.put("cellphoneNumber", borrowerUpdate.getCellphone());
					saveRelation2.put("city", borrowerUpdate.getGeneralInformation().getCity().getCity());
					saveRelation2.put("address", borrowerUpdate.getGeneralInformation().getAddress());
					saveRelation2.put("department",
							borrowerUpdate.getGeneralInformation().getDepartment().getDepartment());
					saveRelation2.put("district",
							borrowerUpdate.getGeneralInformation().getDepartment().getDepartment());
					String bodySaveRelation2 = "";
					try {
						bodySaveRelation2 = objectMapperSaveRelation.writeValueAsString(saveRelation2);
					} catch (JsonProcessingException e3) {
						logger.error(e3.getMessage());
					}
					AWSLambda lambdaClient2 = AWSLambdaClientBuilder.defaultClient();
					InvokeRequest invokeRequest2 = new InvokeRequest().withFunctionName("DemographicCore")
							.withPayload(bodySaveRelation2);
					InvokeResult invokeResult2 = lambdaClient2.invoke(invokeRequest2);
					String resultInvo2 = new String(invokeResult2.getPayload().array());
					resultInvo2 = resultInvo2.replace("[", "");
					resultInvo2 = resultInvo2.replace("]", "");
					logger.info(resultInvo2);
					Data dataAward = mapper.load(Data.class, relationPay.getNumberIdMoneylender());
					saveRelation3.put("numberId", relationPay.getNumberIdBorrower());
					saveRelation3.put("dateMovement", date2);
					saveRelation3.put("typeMovement", "Debito");
					saveRelation3.put("description", "Desembolso");
					saveRelation3.put("value", convertedTx + "");
					saveRelation3.put("product", "P2P");
					saveRelation3.put("account", borrowerUpdate.getCellphone());
					saveRelation3.put("status", "success");
					saveRelation3.put("origenAccount", dataAward.getCellphone());
					saveRelation3.put("destinationAccountId", relationPay.getNumberIdBorrower());
					saveRelation3.put("origenAccountId", relationPay.getNumberIdMoneylender());
					String bodySaveRelation3 = "";
					try {
						bodySaveRelation3 = objectMapperSaveRelation.writeValueAsString(saveRelation3);
					} catch (JsonProcessingException e3) {
						logger.error(e3.getMessage());
					}
					AWSLambda lambdaClient3 = AWSLambdaClientBuilder.defaultClient();
					InvokeRequest invokeRequest3 = new InvokeRequest().withFunctionName("MovementsCore")
							.withPayload(bodySaveRelation3);
					InvokeResult invokeResult3 = lambdaClient3.invoke(invokeRequest3);
					String resultInvo3 = new String(invokeResult3.getPayload().array());
					resultInvo3 = resultInvo3.replace("[", "");
					resultInvo3 = resultInvo3.replace("]", "");
					logger.info(resultInvo3);
					logTx.setAmountTx(convertedTx);
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Transacción exitosa");
					logTx.setStatus("Valid");
					mapper.save(logTx);
				} else {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("transaccion no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				}
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return null;
		case "dispersar":
			transferenciaPowwi = new TransferenciaPowwi[1];
			values = new HashMap<>();
			result = new HashMap<>();
			String cellphone = input.getTransferenciaPowwi()[0].getNumeroDeposito();
			Table table = dynamoDB.getTable("personalInformation");
			Index index = table.getIndex("cellphone-index");
			QuerySpec spec = new QuerySpec().withProjectionExpression("numberId")
					.withKeyConditionExpression("cellphone = :_apply")
					.withValueMap(new ValueMap().withString(":_apply", cellphone));
			ItemCollection<QueryOutcome> items = index.query(spec);
			int a = 0;

			for (Page<Item, QueryOutcome> page : items.pages()) {
				Iterator<Item> iterator = page.iterator();
				Item item = null;
				while (iterator.hasNext()) {
					item = iterator.next();
					values.put("" + a, item.toJSONPretty());
					logger.info(item.toJSONPretty());
					a++;
				}
			}
			int lengthMap1 = values.size();
			if (lengthMap1 == 0) {
				ResponseDispersar response1 = new ResponseDispersar();
				return response1.toString();
			}
			logger.info("tamaño" + lengthMap1);
			for (int j = 0; j < lengthMap1; j++) {
				String hashMap = values.get("" + j).toString();
				hashMap = hashMap.replaceAll("=", ":");
				basicInformation = new Gson().fromJson(hashMap, BasicInformation.class);
				logger.info("hashMap" + hashMap);
				logger.info("basicInformation" + basicInformation.toString());
			}
			double oValue = 0d;
			for (int i = 0; i < n; i++) {
				transferenciaPowwi[i] = input.getTransferenciaPowwi()[i];
				oValue = Double.parseDouble(transferenciaPowwi[i].getMonto());// *(0.997025d);
				System.out.println(oValue);
				transferenciaPowwi[i] = new TransferenciaPowwi(basicInformation.getNumberId(),
						"(+57)" + input.getTransferenciaPowwi()[i].getNumeroDeposito(), "" + oValue,
						input.getTransferenciaPowwi()[i].getReferencia1(),
						input.getTransferenciaPowwi()[i].getReferencia2());
			}
			logger.info(transferenciaPowwi.toString());
			double percentageApprobe = Double.parseDouble(transferenciaPowwi[0].getMonto()) * (0.0025d);
			double oApprobe = percentageApprobe + (percentageApprobe * 0.19d);
//			transferenciaPowwi[n]=new TransferenciaPowwi(idTransaccion, cuentaApprobe,
//					""+oApprobe, input.getTransferenciaPowwi()[0].getReferencia1(), input.getTransferenciaPowwi()[0].getReferencia2());
			basicInformation = mapper.load(BasicInformation.class, input.getIdAutenticacionAliado());
			dispersar.setTokenConvenio(tokenPowwi.getToken());
			dispersar.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			dispersar.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			dispersar.setTransferenciaPowwi(transferenciaPowwi);
			dispersar.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			dispersar.setTipoDispersion(input.getTipoDispersion());
			dispersar.setSO(input.getSO());
			dispersar.setNombreDispositivo(input.getNombreDispositivo());
			dispersar.setIP(input.getIP());
			requestBody = dispersar.toString();
			requestBody = requestBody.replace("\"{", "{");
			requestBody = requestBody.replace("}\"", "}");
			requestBody = requestBody.replace("\"[", "[");
			requestBody = requestBody.replace("]\"", "]");
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/tba/dispersar";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				String[] responseTransaction = answerd.split(",");
				System.out.println(responseTransaction[0]);
				if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_567\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Dispersión no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_502\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Huella del dispositivo invalida");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_509\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Token transaccional inválido");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_200\"")) {
					ResultadoDispersar responseTx = new ResultadoDispersar();
					try {
						String convertedTx = "";
						responseTx = new Gson().fromJson(answerd, ResultadoDispersar.class);// Revisar respuesta
						for (int i = 0; i < responseTx.getTransacciones().length; i++) {
							convertedTx = (" + " + responseTx.getTransacciones()[i].getValorTotalTransaccion())
									+ convertedTx;
						}
						logTx.setAmountTx(convertedTx);
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
					} catch (Exception e) {
						logTx.setAmountTx("");
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
						return answerd;
					}
				} else {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("transaccion no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				}
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.info(e.getMessage());
			}
			return "FAIL";
		case "pagar":
			try {
				factory = new ConnectionFactory();
				factory.setUsername(Env.MQ_USER);
				factory.setPassword(Env.MQ_SECUR);
				factory.setHost(Env.MQ_HOST);
				factory.setPort(Integer.parseInt(Env.MQ_PORT));
				factory.useSslProtocol();
			} catch (KeyManagementException | NoSuchAlgorithmException e1) {
				logger.error("error: "+e1.getMessage());
			}
			transferenciaPowwi = new TransferenciaPowwi[2];
			Relation relation = new Relation();
			Table table1 = dynamoDB.getTable("credit");
			Index index1 = table1.getIndex("numberIdBorrower-index");
			QuerySpec spec1 = new QuerySpec().withProjectionExpression("numberIdBorrower, numberIdMoneylender,id")
					.withKeyConditionExpression("numberIdBorrower = :_apply")
					.withValueMap(new ValueMap().withString(":_apply", input.getIdAutenticacionAliado()));
			ItemCollection<QueryOutcome> items1 = index1.query(spec1);
			int c = 0;

			for (Page<Item, QueryOutcome> page : items1.pages()) {
				Iterator<Item> iterator = page.iterator();
				Item item = null;
				while (iterator.hasNext()) {
					item = iterator.next();
					values.put("" + c, item.toJSONPretty());
					logger.info(item.toJSONPretty());
					c++;
				}
			}
			int lengthMap2 = values.size();

			for (int j = 0; j < lengthMap2; j++) {
				String hashMap = values.get("" + j).toString();
				hashMap = hashMap.replaceAll("=", ":");
				relation = new Gson().fromJson(hashMap, Relation.class);
			}
			String borrowerId = relation.getNumberIdBorrower() == null ? "" : relation.getNumberIdBorrower();
			String lenderId = relation.getNumberIdMoneylender() == null ? "" : relation.getNumberIdMoneylender();
			String idCredit=relation.getId();
			logger.info(borrowerId + " " + lenderId);
			data = mapper.load(Data.class, lenderId);
			if (data == null) {
				data = new Data();
			}
			double oValue1 = 0d;
			for (int i = 0; i < n; i++) {
				transferenciaPowwi[i] = input.getTransferenciaPowwi()[i];
				oValue1 = Double.parseDouble(transferenciaPowwi[i].getMonto());// *(0.997025d);
				logger.info(oValue1 + "");
				transferenciaPowwi[i] = new TransferenciaPowwi(data.getNumberId(), "(+57)" + data.getCellphone(),
						"" + oValue1, input.getTransferenciaPowwi()[i].getReferencia1(),
						input.getTransferenciaPowwi()[i].getReferencia2());
			}
			logger.info(transferenciaPowwi.toString());
			percentageApprobe = Double.parseDouble(transferenciaPowwi[0].getMonto()) * (0.0025d);
			oApprobe = percentageApprobe + (percentageApprobe * 0.19d);
			transferenciaPowwi[n] = new TransferenciaPowwi(idTransaccion, cuentaApprobe, "" + oApprobe,
					input.getTransferenciaPowwi()[0].getReferencia1(),
					input.getTransferenciaPowwi()[0].getReferencia2());
			basicInformation = mapper.load(BasicInformation.class, input.getIdAutenticacionAliado());
			dispersar.setTokenConvenio(tokenPowwi.getToken());
			dispersar.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			dispersar.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			dispersar.setTransferenciaPowwi(transferenciaPowwi);
			dispersar.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			dispersar.setTipoDispersion(input.getTipoDispersion());
			dispersar.setSO(input.getSO());
			dispersar.setNombreDispositivo(input.getNombreDispositivo());
			dispersar.setIP(input.getIP());
			requestBody = dispersar.toString();
			requestBody = requestBody.replace("\"{", "{");
			requestBody = requestBody.replace("}\"", "}");
			requestBody = requestBody.replace("\"[", "[");
			requestBody = requestBody.replace("]\"", "]");
			System.out.println("respuesta " + requestBody);
			path = "/MisPesosAPI/tba/dispersar";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				String[] responseTransaction = answerd.split(",");
				if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_567\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Dispersión no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_502\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Huella del dispositivo invalida");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_509\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Token transaccional inválido");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_200\"")) {
					ResultadoDispersar responseTx = new ResultadoDispersar();
					try {
						String convertedTx = "";
						responseTx = new Gson().fromJson(answerd, ResultadoDispersar.class);// Revisar respuesta
						for (int i = 0; i < responseTx.getTransacciones().length; i++) {
							convertedTx = (" + " + responseTx.getTransacciones()[i].getValorTotalTransaccion())
									+ convertedTx;
						}
						logTx.setAmountTx(convertedTx);
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
						HashMap<String, String> value = new HashMap<String, String>();
						String body = "";
						value.put("idCode", idCredit);
						value.put("channel", "P2P");
						value.put("ammount", "" + responseTx.getTransacciones()[0].getValorTotalTransaccion());
						value.put("ticketId", responseTx.getTransacciones()[0].getIdOperacionAliado());
						try {
							ObjectMapper objectMapper = new ObjectMapper();
							body = objectMapper.writeValueAsString(value);
							connection = factory.newConnection();
							channel = connection.createChannel();
							channel.basicPublish(Env.EXCHANGE_NAME, Env.QUEUE_NAME, null, body.getBytes("UTF-8"));
							context.getLogger().log("objeto cola: " + body);
							channel.close();
							connection.close();
						} catch (Exception e3) {
							context.getLogger().log(e3.getMessage());
						}

					} catch (Exception e) {
						logTx.setAmountTx("");
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
						return answerd;
					}
				} else {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("transaccion no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				}
				return answerd;
			} catch (IOException | InterruptedException e) {
				logger.error(e.getMessage());
			}
			return "FAIL";
		case "transferirOtraEntidad":
			result = new HashMap<>();
			for (int i = 0; i < n; i++) {
				transferenciaOtraEntidad[i] = input.getTransferenciaOtraEntidad()[i];
			}
			Data data1 = mapper.load(Data.class, input.getIdAutenticacionAliado());
			basicInformation = mapper.load(BasicInformation.class, input.getIdAutenticacionAliado());
			List<String> resultArray = new ArrayList<String>();
			dispersarOtra.setTokenConvenio(tokenPowwi.getToken());
			dispersarOtra.setIdCuenta(basicInformation.getIdCuenta() == null ? "" : basicInformation.getIdCuenta());
			dispersarOtra
					.setHuellaDispositivo(basicInformation.getHuella() == null ? "" : basicInformation.getHuella());
			dispersarOtra.setTransferenciaOtraEntidad(transferenciaOtraEntidad);
			dispersarOtra.setTokenTransaccional(
					basicInformation.getTokenTransaccional() == null ? "" : basicInformation.getTokenTransaccional());
			dispersarOtra.setTipoDispersion(input.getTipoDispersion());
			dispersarOtra.setSO(input.getSO());
			dispersarOtra.setNombreDispositivo(input.getNombreDispositivo());
			dispersarOtra.setIP(input.getIP());
			requestBody = dispersarOtra.toString();
			requestBody = requestBody.replace("\"{", "{");
			requestBody = requestBody.replace("}\"", "}");
			requestBody = requestBody.replace("\"[", "[");
			requestBody = requestBody.replace("]\"", "]");
			logger.info("respuesta " + requestBody);
			path = "/MisPesosAPI/tba/dispersar";
			requestPayload = requestBody;
			headers = new TreeMap<String, String>();
			headers.put("host", HOST);
			awsv4Auth = new AWSV4Auth.Builder(Env.AUTH_ACCESS_KEY, Env.AUTH_SECRET_KEY).path(path).region(region)
					.service("execute-api")// execute-api
					.httpMethodName("POST ").headers(headers).payload(requestPayload).build();
			header = awsv4Auth.getHeaders();
			try {
				String answerd = clientHttp("https://" + HOST, path, header, requestBody);
				String[] responseTransaction = answerd.split(",");
				System.out.println(responseTransaction[0]);
				if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_567\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Dispersión no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_502\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Huella del dispositivo invalida");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_509\"")) {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("Token transaccional inválido");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				} else if (Arrays.asList(responseTransaction).contains("{\"codigoResultado\":\"TBA_200\"")) {
					ResultadoDispersar responseTx = new ResultadoDispersar();
					try {
						String convertedTx = "";
						responseTx = new Gson().fromJson(answerd, ResultadoDispersar.class);// Revisar respuesta
						System.out.println(responseTx.toString());
						for (int i = 0; i < responseTx.getTransacciones().length; i++) {
							convertedTx = (" + " + responseTx.getTransacciones()[i].getValorTotalTransaccion())
									+ convertedTx;
						}
						logTx.setAmountTx(convertedTx);
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
					} catch (Exception e) {
						logTx.setAmountTx("");
						logTx.setNumberId(input.getIdAutenticacionAliado());
						logTx.setRequestTx(requestBody);
						logTx.setResponseTx(answerd);
						logTx.setResultTx("Transacción exitosa");
						logTx.setStatus("Valid");
						mapper.save(logTx);
						return answerd;
					}
				} else {
					logTx.setAmountTx("0");
					logTx.setNumberId(input.getIdAutenticacionAliado());
					logTx.setRequestTx(requestBody);
					logTx.setResponseTx(answerd);
					logTx.setResultTx("transaccion no realizada");
					logTx.setStatus("Fail");
					mapper.save(logTx);
				}
				String resp = "{\"celularOrigen\":\"" + data1.getCellphone() + "\"}";
				resultArray.add(answerd);
				resultArray.add(resp);
				return resultArray.toString();
			} catch (IOException | InterruptedException e) {
				logger.info(e.getMessage());
			}
			return null;

		}
		return null;
	}

	private static String clientHttp(String host, String pathUrl, Map<String, String> header, String requestBody)
			throws JsonProcessingException, IOException, InterruptedException {
		String responsea = "";
		try {
			HttpClient clienta = HttpClient.newHttpClient();

			HttpRequest requestb = HttpRequest.newBuilder().uri(URI.create(host + pathUrl))
					.header("Content-Type", "application/json").header("userName", Env.AUTH_USERNAME)
					.header("x-amz-date", header.get("x-amz-date")).header("Authorization", header.get("Authorization"))
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

			HttpResponse<String> responsee = clienta.send(requestb, HttpResponse.BodyHandlers.ofString());
			responsea = responsee.body();
			logger.info("respuesta " + responsee.body());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		return responsea;
	}

	private byte[] hmacSha256(byte[] key, String data) throws Exception {
		String algorithm = "HmacSHA256";
		Mac mac = Mac.getInstance(algorithm);
		mac.init(new SecretKeySpec(key, algorithm));
		return mac.doFinal(data.getBytes("UTF-8"));
	}

	private final char[] hexArray = "0123456789ABCDEF".toCharArray();

	private String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars).toLowerCase();
	}

}
