package co.approbe.powwi.transaction.dao;

public class CreditInformation {
	private Purpose purpose;
	private String descriptionPurpose;
	private Profession profession;
	private LaborInformation laborInformation;
	private PensionInformation pensionInformation;
	private Freelancer freelancer;

	@Override
	public String toString() {
		return "CreditInformation [purpose=" + purpose  + ", descriptionPurpose=" + descriptionPurpose
				  + ", profession=" + profession + ", laborInformation="
				+ laborInformation + ", pensionInformation=" + pensionInformation + ", freelancer=" + freelancer + "]";
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	
	public String getDescriptionPurpose() {
		return descriptionPurpose;
	}

	public void setDescriptionPurpose(String descriptionPurpose) {
		this.descriptionPurpose = descriptionPurpose;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public LaborInformation getLaborInformation() {
		return laborInformation;
	}

	public void setLaborInformation(LaborInformation laborInformation) {
		this.laborInformation = laborInformation;
	}

	public PensionInformation getPensionInformation() {
		return pensionInformation;
	}

	public void setPensionInformation(PensionInformation pensionInformation) {
		this.pensionInformation = pensionInformation;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}

//	public static class Profession {
//		private String profession;
//
//		public String getProfession() {
//			return profession;
//		}
//
//		public void setProfession(String profession) {
//			this.profession = profession;
//		}
//
//		@Override
//		public String toString() {
//			return "Profession [profession=" + profession + "]";
//		}
//
//	}

	public static class LaborInformation {
		private String companyName;
		private String position;
		private String CompanyPhone;
		private CompanyActivity companyActivity;
		private String address;
		private String nit;
		

		public String getNit() {
			return nit;
		}

		public void setNit(String nit) {
			this.nit = nit;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getCompanyPhone() {
			return CompanyPhone;
		}

		public void setCompanyPhone(String companyPhone) {
			CompanyPhone = companyPhone;
		}

		public CompanyActivity getCompanyActivity() {
			return companyActivity;
		}

		public void setCompanyActivity(CompanyActivity companyActivity) {
			this.companyActivity = companyActivity;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "LaborInformation [companyName=" + companyName + ", position=" + position + ", CompanyPhone="
					+ CompanyPhone + ", companyActivity=" + companyActivity + ", address=" + address + ", nit=" + nit
					+ "]";
		}

	}

	public static class CompanyActivity {
		private Activity activity;

		public Activity getActivity() {
			return activity;
		}

		public void setActivity(Activity activity) {
			this.activity = activity;
		}

		public static class Activity {
			private int id;
			private String description;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

		}

	}

	public static class PensionInformation {
		private String information;
		private String nit;
		
		public String getNit() {
			return nit;
		}

		public void setNit(String nit) {
			this.nit = nit;
		}

		public String getInformation() {
			return information;
		}

		public void setInformation(String information) {
			this.information = information;
		}

		@Override
		public String toString() {
			return "PensionInformation [information=" + information + ", nit=" + nit + "]";
		}

	}

	public static class Freelancer {
		private String mainContractorName;
		private String nit;
		private ActivityContractor activityContractor;
		private String codeCIIU;
		private int employeeNumber;
		private int experienceYears;
		private String commercialAddress;
		private ComercialCity comercialCity;
		private String commercialPhone;
		private String service;

		public String getMainContractorName() {
			return mainContractorName;
		}

		public void setMainContractorName(String mainContractorName) {
			this.mainContractorName = mainContractorName;
		}

		public String getNit() {
			return nit;
		}

		public void setNit(String nit) {
			this.nit = nit;
		}

		public String getCodeCIIU() {
			return codeCIIU;
		}

		public void setCodeCIIU(String codeCIIU) {
			this.codeCIIU = codeCIIU;
		}

		public int getEmployeeNumber() {
			return employeeNumber;
		}

		public void setEmployeeNumber(int employeeNumber) {
			this.employeeNumber = employeeNumber;
		}

		public int getExperienceYears() {
			return experienceYears;
		}

		public void setExperienceYears(int experienceYears) {
			this.experienceYears = experienceYears;
		}

		public String getCommercialAddress() {
			return commercialAddress;
		}

		public void setCommercialAddress(String commercialAddress) {
			this.commercialAddress = commercialAddress;
		}


		public String getCommercialPhone() {
			return commercialPhone;
		}

		public void setCommercialPhone(String commercialPhone) {
			this.commercialPhone = commercialPhone;
		}

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}

		@Override
		public String toString() {
			return "Freelancer [mainContractorName=" + mainContractorName + ", nit=" + nit + ", activityContractor="
					+ activityContractor + ", codeCIIU=" + codeCIIU + ", employeeNumber=" + employeeNumber
					+ ", experienceYears=" + experienceYears + ", commercialAddress=" + commercialAddress
					+ ", commercialCity=" + comercialCity + ", commercialPhone=" + commercialPhone + ", service="
					+ service + "]";
		}

		public static class ActivityContractor {
			private int id;
			private String description;
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			

		}
		
		public static class ComercialCity {
			private int id;
			private String description;
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			

		}

	}

}
