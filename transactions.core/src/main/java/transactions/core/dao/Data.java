package transactions.core.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "authorizationData")
public class Data {
	@DynamoDBAttribute
	@DynamoDBAutoGeneratedKey
	private String id;
	
	@DynamoDBAttribute
	private String identification;
	
	@DynamoDBHashKey
	private String numberId;
	
	@DynamoDBAttribute
	private String expeditionDate;
	
	@DynamoDBAttribute
	private Integer flagExist;
	
	@DynamoDBAttribute
	private Integer countExperian;
	
	@DynamoDBAttribute
	private String names;
	
	@DynamoDBAttribute
	private String lastName;
	
	@DynamoDBAttribute
	private String cellphone;
	
	@DynamoDBAttribute
	private String gender;
	
	@DynamoDBAttribute
	private String email;
	
	@DynamoDBAttribute
	private String habeas;
	
	@DynamoDBAttribute
	private String tyc;
	
	@DynamoDBAttribute
	private String firm;
	
	@DynamoDBAttribute
	private Integer fail;
	
	@DynamoDBAttribute
	private String statusFail;
	
	@DynamoDBAttribute
	private String statusProceed;
	
	@DynamoDBAttribute
	private String log;
	
	@DynamoDBAttribute
	private Integer count;
	
	
	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "habeasData")
	private String habeasData="De acuerdo con lo establecido por la ley colombiana y demás autoridades pertinentes, declaró que: SUMINISTRO Y ACTUALIZACIÓN DE LA INFORMACIÓN. El Usuario se obliga con DUVERA KAPITAL SAS en adelante “Duvera” a entregar información veraz y verificable y a actualizar su información personal, comercial y financiera, por lo menos una (1) vez al año, o cada vez que así lo solicite Duvera, suministrando la totalidad de los soportes documentales exigidos. El incumplimiento de esta obligación, faculta a Duvera para terminar de manera inmediata y unilateral cualquier tipo de relación que tenga con el Usuario. AUTORIZACIÓN PARA LA CONSULTA, REPORTE Y PROCESAMIENTO DE DATOS CREDITICIOS, FINANCIEROS, COMERCIALES, DE SERVICIOS Y DE TERCEROS PAÍSES EN LA CENTRAL DE INFORMACIÓN DATACREDITO Y A CUALQUIER OTRA ENTIDAD PÚBLICA O VADA DE CENTRALES DE RIESGO. En mi calidad de titular de información, actuando libre y voluntariamente, autorizo de manera expresa e irrevocable a Duvera, o a quien represente sus derechos, a consultar, solicitar, suministrar, reportar, procesar y divulgar toda la información que se refiera a mi comportamiento crediticio, financiero, comercial, de servicios y de terceros países de la misma naturaleza, a la Centrales de Información Crediticia legalmente constituidas o a cualquier otra entidad pública o privada que administre o maneje bases de datos, cualquier otra entidad financiera de Colombia, o a quien represente sus derechos. Conozco que el alcance de esta autorización implica que el comportamiento frente a mis obligaciones será registrado con el objeto de suministrar información suficiente y adecuada al mercado sobre el estado de mis obligaciones financieras, comerciales, crediticias, de servicios y la proveniente de terceros países de la misma naturaleza. En consecuencia, quienes se encuentren afiliados y/o tengan acceso a la Central de Información Expirian (Datacrédito) o a cualquier otra entidad pública o privada que administre o maneje bases de datos podrán conocer esta información, de conformidad con la legislación y jurisprudencia aplicable. La información podrá ser igualmente utilizada para efectos estadísticos, evaluación de riesgo y gestión de cobranza. Mis derechos y obligaciones, así como la permanencia de mi información en las bases de datos corresponden a lo determinado por el ordenamiento jurídico aplicable del cual, por ser de carácter público, estoy enterado. En caso de que, en el futuro, el autorizado en este documento efectúe, a favor de un tercero, una venta de cartera o una cesión a cualquier título de las obligaciones a mi cargo, los efectos de la presente autorización se extenderán a éste en los mismos términos y condiciones. Así mismo, autorizo a la central de información a que, en su calidad de operador, ponga mi información a disposición de otros operadores nacionales o extranjeros, en los términos que establece la ley, siempre y cuando su objeto sea similar al aquí establecido. Igualmente, autorizo el tratamiento de mis datos crediticios, financieros y comerciales de conformidad con los fines y parámetros establecidos en la Ley 1266 de 2008 y cualquier norma que la modifique, adicione o sustituya. AUTORIZACIÓN GENERAL PARA MANEJO DE DATOS PERSONALES. En adición y complemento de las autorizaciones previamente otorgadas, autorizo de manera expresa y previa sin lugar a pagos ni retribuciones a Duvera, cesionarios o a quien represente los derechos, para que efectúe el Tratamiento de mis Datos Personales de la manera y para las finalidades que se señalan a continuación. Para efectos de la presente autorización, se entiende por 'Datos Personales' la información personal de carácter financiero, crediticio, comercial, profesional, sensible (definidas en la ley tales como mis huellas, imagen o voz entre otros), técnico y administrativo, privada, semiprivada pasada, presente o futura, contenida en medios físicos, digitales o electrónicos y por 'Tratamiento de datos personales' todo tipo de actividad, acción, operación o proceso, incluyendo pero sin limitarse a: recolectar, consultar, recopilar, evaluar, catalogar, clasificar, ordenar, grabar, almacenar, actualizar, modificar, aclarar, reportar, informar, analizar, utilizar, compartir, circularizar, suministrar, suprimir, procesar, solicitar, verificar, intercambiar, retirar, transferir, transmitir, disponer, organizar, ajustar, modelar, unir, consolidar, fusionar, combinar, agregar, resumir, digitalizar, explotar, rentabilizar, aprovechar, emplear y/o divulgar, y, efectuar operaciones sobre mis Datos Personales. La autorización que otorgo a Duvera por el presente medio para el tratamiento de mis Datos Personales tendrá las siguientes finalidades: a. Promocionar, comercializar u ofrecer, de manera individual o conjunta productos y/o servicios propios u ofrecidos en alianza comercial, a través de los canales autorizados en la ley o contrato, o para complementar, optimizar o profundizar el portafolio de productos y/o servicios actualmente ofrecidos. b. Como elemento de análisis en etapas pre-contractuales, contractuales y post-contractuales para establecer y/o mantener relaciones contractuales, incluyendo como parte de ello, los siguientes propósitos: i. Evaluar riesgos derivados de la relación contractual potencial, vigente o concluida, ii. Realizar, validar, autorizar o verificar transacciones incluyendo, cuando sea requerido, la consulta y reproducción de datos sensibles tales como mi huella, imagen o voz, iii. Obtener conocimiento de mi perfil comercial o transaccional. iv. Conocer sobre el nacimiento, modificación, celebración, extinción, novedades, hábitos de pago, comportamiento crediticio o cumplimiento de obligaciones directas, contingentes o indirectas, de las obligaciones vigentes, activas o pasivas, o las que en el futuro llegue a celebrar, incluyendo información referente al manejo, estado, cumplimiento de mis obligaciones de pago derivadas de las relaciones, contratos y servicios, hábitos de pago, incluyendo aportes al sistema de seguridad social, obligaciones y las deudas vigentes, vencidas sin cancelar, procesos, o la utilización indebida de servicios financieros, en bases de datos administradas directamente por las partes contratantes, por operadores de información financiera, autoridades o entidades estatales, operadores de información y/o entidades que formen parte del Sistema de Seguridad Social Integral, empresas prestadoras de servicios públicos y de telefonía móvil, o administrador de bases de datos o entidades similares que en un futuro se establezca y que tenga por objeto alguna de las anteriores actividades. v. Ejercer mis derechos, incluyendo los referentes a actividades de cobranza judicial y extrajudicial y las gestiones conexas para obtener el pago de las obligaciones a mi cargo o de mi empleador. v. Recolectar y entregar información a autoridades extranjeras con competencia sobre Duvera o sobre sus actividades, productos y/o servicios, cuando se requiera para dar cumplimiento a sus deberes legales o reglamentarios, incluyendo dentro de éstos, aquellos referentes a la prevención de la evasión fiscal, lavado de activos y financiación del terrorismo u otros propósitos similares. Para efectos de lo dispuesto en el presente literal c. Duvera en lo que resulte aplicable, podrá efectuar el Tratamiento de mis Datos Personales ante entidades de consulta, que manejen o administren bases de datos para los fines legalmente definidos, domiciliadas en Colombia o en el exterior, sean personas naturales o jurídicas, colombianas o extranjeras, d. Realizar ventas cruzadas de productos y/o servicios ofrecidos por Duvera, incluyendo la celebración de convenios de marca compartida, e. Elaborar y reportar información contenida en encuestas de satisfacción, estudios y análisis de mercado, para lo cual autorizo la posibilidad de contactarme para dichos propósitos, f. Enviar mensajes a través de medios físicos o digitales, sobre promociones, campañas comerciales, publicitarias, de mercadeo, sorteos, eventos u otros beneficios, así como dar a conocer otros servicios y/o productos ofrecidos por Duvera o sus aliados comerciales, g. Enviar notificaciones a través de medios físicos o digitales, para remitir extractos, campañas institucionales o de educación financiera e informar acerca de las innovaciones o modificaciones efectuadas en sus productos y/o servicios, dar a conocer las mejoras o cambios en sus canales de atención, h. Para que mis Datos Personales puedan ser utilizados como medio de prueba. Mis Datos Personales podrán consultarse, circular, transmitirse y/o transferirse a la totalidad de las áreas de Duvera así como a los proveedores de servicios, entidades públicas o que presten servicios públicos tales como notarías, Registraduría nacional del Estado Civil, Contraloría, Procuraduría, DIAN, oficinas de registro, cajas de compensación, administradoras de fondos de pensiones y de cesantías, operadores de información a través de las cuales se liquidan cesantías, aportes a seguridad social y parafiscales (tales como Aportes en Línea, SOI, SIMPLE), usuarios de red, redes de distribución y personas que realicen la promoción de sus productos y servicios, incluidos call centers, domiciliados en Colombia o en el exterior, sean personas naturales o jurídicas, colombianas o extranjeras a su fuerza comercial, equipos de telemercadeo y/o procesadores de datos que trabajen en nombre de Duvera, incluyendo contratistas, delegados, outsourcing, tercerización, red de oficinas o aliados, con el objeto de desarrollar servicios de alojamiento de sistemas, de mantenimiento, servicios de análisis, servicios de mensajería por mensaje de texto, WhatsApp, e-mail o correo físico, servicios de entrega, gestión de transacciones de pago y cobranza, implementación de software o servicios tecnológicos (en adelante los 'Terceros') los cuales están obligados a garantizar la reserva de la información de acuerdo con lo estipulado en la Ley 1581 de 2012. En consecuencia, como titular, entiendo y acepto que mediante la presente autorización concedo a estos Terceros, autorización para: i. Acceder a mis Datos Personales en la medida en que así lo requieran para la prestación de los servicios para los cuales fueron contratados y sujeto al cumplimiento de los deberes que les correspondan como encargados del Tratamiento de mis Datos Personales. ii. Compartir mis Datos Personales con las entidades gremiales a las que pertenezca la entidad, para fines comerciales, estadísticos y de estudio y análisis de mercadeo. Es entendido que las personas naturales y jurídicas, nacionales y extranjeras mencionadas anteriormente ante las cuales Duvera puede llevar a cabo el Tratamiento de mis Datos Personales, también cuentan con mi autorización para permitir dicho Tratamiento. Adicionalmente, mediante el otorgamiento de la presente autorización, manifiesto: (i) que los Datos Personales suministrados son veraces, verificables y completos, (ii) que conozco y entiendo que el suministro de la presente autorización es voluntaria, razón por la cual no me encuentro obligado a otorgar la presente autorización, (iii) que conozco y entiendo que mediante la simple presentación de una comunicación escrita puede limitar en todo o en parte el alcance de la presente autorización, y (iv) haber sido informado sobre mis derechos a conocer, actualizar y rectificar mis Datos Personales, el carácter facultativo de mis respuestas a las preguntas que sean hechas cuando versen sobre datos sensibles o sobre datos de los niños, niñas o adolescentes, solicitar prueba de la autorización otorgada para su tratamiento, ser informado sobre el uso que se ha dado a los mismos, presentar quejas ante la autoridad competente por infracción a la ley una vez haya agotado el trámite de consulta o reclamo ante Duvera, revocar la presente autorización, solicitar la supresión de mis datos en los casos en que sea procedente y ejercer en forma gratuita mis derechos y garantías constitucionales y legales. El tratamiento de mis Datos Personales se efectuará de acuerdo con la política de Duvera en la materia, la cual puede ser consultada en la página www.duveracapital.com.co Para el ejercicio de mis derechos, y la atención de consultas y reclamos, podré contactarme a la línea de servicio (601) 2180616 Bogotá o al correo electrónico comercial@duverak.com AUTORIZACIÓN PARA EL TRATAMIENTO DE DATOS DE SEGURIDAD SOCIAL. En adición a las autorizaciones previamente otorgadas, autorizo de manera expresa, inequívoca, voluntaria y suficiente a Duvera para solicitar, tratar, consultar, recolectar, almacenar, analizar, verificar, usar o circular la información personal o comercial relativa a mis ingresos y aportes obligatorios y/o voluntarios en salud, pensión, y cesantías de las entidades de seguridad social, fondos de pensiones o cesantías y/o otra entidad similar en que se encuentre afiliado, que reposa o sea administrada por los operadores de información (PILA), aliados tecnológicos y demás entidades que formen parte del sistema de Seguridad Social, y a éstos a su vez para que le suministren a Duvera, por el medio que considere pertinente y seguro, mis Datos Personales relacionados con la afiliación y pago de los aportes al Sistema de Seguridad Social Integral, tales como ingreso base de cotización y demás información relacionada con mi situación laboral y empleador. Duvera podrá conocer dicha información cuantas veces lo requiera, mantenerla actualizada y en general tratarla, directamente o través de un encargado, con la finalidad de analizar mi perfil crediticio en aras de establecer una relación comercial y/o de servicios conmigo, así como también para ofrecerme productos o servicios que se adecuen a mi perfil crediticio.\r\n"
			+ "\r\n"
			+ "";
	
	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "tycData")
	private String tycData="DUVERA KAPITAL SAS en adelante “Duvera” pone a disposición de los Usuarios que decidan navegar en el portal transaccional en adelante el “sitio Web” creado y administrado por Duvera, de acuerdo con el artículo 53 de la ley 1480 de 2011. Los Usuarios quienes a su vez podrán ser clientes de Duvera o de sus filiales y/o subsidiarias, tendrán la oportunidad en el sitio Web de vincularse y autenticarse para contar con una experiencia personalizada, de acuerdo con los presentes Términos y Condiciones sobre el uso y el acceso al que a continuación se detalla. 1.Aceptación de políticas de portal Transaccional El ingreso al sitio Web y/o descarga de nuestra app es un acto inequívoco de aceptación de las presentes políticas de uso, el Usuario al ingresar al sitio Web www.duveracapital.com.co, o sus subdominios, acepta las presentes políticas, además la navegación en el sitio Web es un claro indicio de que el Usuario conoce las políticas y las acepta. 2. Uso del Portal Transaccional La utilización del Sitio Web por parte del Usuario se ceñirá estrictamente a lo establecido en el presente documento de términos y condiciones de uso; a través del sitio Web, el Usuario podrá acceder y utilizar los servicios allí contenidos y puestos a su disposición por parte de Duvera. Duvera eventualmente podrá negar, restringir o condicionar el acceso y uso del sitio Web al Usuario, total o parcialmente a su entera discreción y en los casos que así lo considere, y de la misma manera, tendrá el derecho de modificar y/o todo o parte del contenido del sitio Web en cualquier momento y sin necesidad de previo aviso. En todo caso, el Usuario no podrá usar el sitio para: 1. Realizar actividades que vayan en contra de la ley mediante el sitio Web. 2. Violentar informáticamente el Sitio Web. 3. Todas aquellas conductas contrarias al presente documento y que pueda degenerar en una afectación grave a Duvera así como a sus Usuarios. 3. Forma de uso del Portal Transaccional Duvera coloca a disposición del Usuario el sitio Web, como un canal de comunicación de los servicios que Duvera ofrece, así como los beneficios de ser parte de éste. Por otra parte, Duvera coloca a disposición del Usuario el sitio Web, como un canal de comunicación entre las partes, para la gestión de los productos y/o servicios contratados con Duvera o con terceros; esta administración hace referencia a: 1. Conocer el estado actual e información adicional de sus productos y/o servicios contratados. 2. Realizar transacciones financieras de sus productos contratados. 3. Adquirir nuevos productos ofertados por Duvera.4. Actualizar información personal. 5. Otras transacciones que se habiliten en el futuro y se pongan a disposición del Usuario. 6. Solicitar trámites. En general, el Usuario mediante la creación, adjudicación y/o habilitación por parte Duvera, de su Usuario y contraseña de ingreso, podrá identificarse y ejecutar las diferentes transacciones y operaciones que estén disponibles para su utilización. 4. Acceso al Portal Transaccional Tienen acceso a los servicios del sitio Web, los Usuarios de Duvera a quienes este les haya suministrado o hayan generado un Usuario y una clave de ingreso al mismo, que será contraseñas (OTP u otros), datos biométricos, firma electrónica o cualquier otro mecanismo que identificará al Usuario en sus relaciones con Duvera. Los Usuarios de Duvera se obligan a mantener en absoluta reserva dicho Usuario y clave de ingreso, a fin de que nadie más que él tenga acceso a los servicios ofrecidos; por lo tanto, el Usuario no podrá ceder ni hacerse sustituir por terceros en el ejercicio de los derechos y compromisos que se le imponen. El Usuario será responsable por el incumplimiento de la obligación que aquí asume. Duvera estará facultado para exigir en ciertas transacciones, seguridades adicionales, tales como una segunda clave, segundo Usuario o cualquier otro componente que lo permita. Los Usuarios de Duvera conocen y aceptan que los registros electrónicos de los contratos suscritos, de las operaciones y/o transacciones efectuadas que se originen bajo la firma electrónica y/o seguridades adicionales, constituyen medios de prueba para todos los efectos legales. Para poder acceder al Servicio, el Usuario deberá disponer de los medios físicos que le permitan recibir o transmitir vía Internet la información aquí prevista. El Usuario adquirirá y mantendrá a su propio costo y gasto todo el equipo y los medios de comunicación necesarios para utilizar el Servicio, y Duvera no se hará responsable de la disponibilidad ni de la confiabilidad de dicho equipo o de los medios de comunicaciones. 5. Veracidad de la información Los Usuarios se comprometen a presentar a Duvera información veraz y actualizada acerca de su situación personal y financiera a fin de utilizar adecuadamente tanto el sitio Web, así como las claves de ingreso y mecanismos de seguridad que sean establecidos por Duvera. 6. Confidencialidad Las credenciales entregadas por Duvera y/o generadas por los Usuarios tales como Usuario y contraseña de ingreso, contraseña dinámica, respuestas de seguridad y las demás que se dispongan en el futuro, son personales e intransferibles y será el Usuario el responsable de mantener la privacidad de sus contraseñas. Las Partes aceptan que sin la utilización de las contraseñas no se podrá ingresar al sistema ni realizar ningún tipo de transacción, solicitud, traslado, consulta, movimiento, pago, servicio o descuento por servicio. 7. Disponibilidad del Portal Transaccional Duvera, o el tercero al cual este designe para tal fin, procurará mantener el sitio Web activo 24 horas al día, 7 días por semana, los 365 días del año. Sin embargo, Duvera se reserva el derecho y la facultad de restringir, suspender o interrumpir el servicio de forma temporal o permanente por razones de seguridad, mantenimiento técnico, o por aquellas circunstancias que puedan llegar a presentarse en la prestación del servicio, caso fortuito o hecho de un tercero, limitaciones y/o restricciones que serán informadas previamente por Duvera a través de los canales dispuestos para ello. 8. Autorización Información El Usuario entiende y autoriza que sus datos sean almacenados de forma segura en nuestro sitio Web, así como sus usos comunes y su perfil transaccional. Duvera podrá compartir, almacenar, tratar, la información necesaria con la finalidad que la misma sea utilizada para la prestación del servicio del Sitio Web, el cual podrá ser prestado por un tercero y teniendo en cuenta los estándares de seguridad respectivos, y que requiera conocer con fines transaccionales y para la correcta prestación del servicio; todo lo anterior de acuerdo con la Política de Protección de Datos Personales que podrá consultar en el sitio Web. 9. Exoneración de Responsabilidad Duvera no será responsable, entre otros, por los siguientes eventos: a. Por el uso indebido del servicio por sí mismo o por personas no autorizadas, por lo que el Usuario asume la responsabilidad de las transacciones y los movimientos ordenados mediante el canal y que hayan desarrollado adecuadamente las condiciones de seguridad. b. Fuerza mayor, caso fortuito, causa extraña o hecho de un tercero y que en un momento puedan ocasionar perjuicios al Cliente que imposibilite, demore, desvíe o altere la realización de operaciones y transacciones. c. En el caso en que las causas de la falla o rechazo de la operación sean atribuibles al Usuario tales como incorrecta operación del sistema, información insuficiente en los formularios y solicitudes, daños en los sistemas de transmisión de datos u otros ajenos al control de Duvera. d. Frente a sitios de terceros, proveedores y cualquier otra persona. Duvera se encuentra desligado de las relaciones comerciales y contractuales realizadas entre el Usuario y el tercero. De esta forma no existe responsabilidad alguna de Duvera sobre la fecha, valor, condiciones o información de terceros con los cuales el Usuario realice sus transacciones o movimientos financieros a través del sitio Web de Duvera, así como tampoco por los errores o desinformación que este tercero presente al Usuario. 10. Límites en la operación del Portal Transaccional Duvera se encuentra facultado para bloquear parcialmente o cancelar definitivamente los servicios y/o operaciones cuando el producto del Usuario presente o llegare a presentar alguna(s) de las siguientes condiciones: Irregularidad en la utilización del servicio. b. Como medida de seguridad para el Usuario o para Duvera en caso de encontrar movimientos sospechosos o extraños al comportamiento del Usuario. c. En caso de que el Usuario llegara a ser vinculado por parte de las autoridades en actividades ilícitas, tales como: narcotráfico, terrorismo, secuestro, lavado de activos, financiación del terrorismo y administración de recursos relacionados con actividades terroristas u otros delitos relacionados con el lavado de activos y financiación del terrorismo. 11. Convenios Duvera se encuentra habilitado para realizar de forma autónoma convenios con terceros para mejorar la prestación del servicio del sitio Web, en este sentido, el Usuario, bajo las credenciales creadas por él o suministradas por Duvera, podrá identificarse ante los terceros con los cuales Duvera tenga convenios y ejecutar las operaciones disponibles a ese momento. 12. Cobros y Tarifas Duvera se encuentra en la facultad de cobrar al Usuario las tarifas y/o comisiones que defina por concepto de la utilización de los diferentes servicios, productos o transacciones que ofrece al Usuario. Estas tarifas podrán estar enmarcadas bajo los conceptos de valor de la operación, tiempo de conexión, número de operaciones realizadas o número de Usuarios. Duvera avisará a través de los canales que estime como convenientes para informar al Usuario.\r\n"
			+ "\r\n"
			+ "";
	
	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "signatureData")
	private String signatureData="A través de este documento, Usted como Usuario, acuerda las condiciones sobre el uso de una firma electrónica por su parte, la cual le permitirá identificarlo personalmente, así como aceptar y reconocer el contenido de los documentos o mensajes de datos que son requeridos para continuar con el trámite de su solicitud, en los términos que a continuación se detalla: De conformidad con el Decreto 2364 de 2012, el Usuario y Duvera Kapital SAS de manera expresa acuerdan que a partir de la fecha, los códigos (OTP u otros), contraseñas, datos biométricos o cualquier otro mecanismo, constituyen técnicas de identificación personal o autenticación electrónica, apropiados y confiables, y acuerdan que cumplen los requisitos de firma electrónica para el reconocimiento de la autoría del Usuario y contenido de los siguientes formatos: 1º. Solicitud y aceptación de Productos y Servicios Financieros por parte del Cliente. 2º. Cualquier otra autorización o declaración impartida durante la solicitud del Producto; reconociendo el contenido y suscripción de los documentos electrónicos o términos y condiciones. 3º. Autenticación para operaciones no monetarias. El Usuario se obliga a mantener, custodiar y controlar los datos de creación de la firma y guardar confidencialidad sobre la misma, así como abstenerse de conocer o descifrar el código fuente del mecanismo utilizado. El Usuario declara que esta firma electrónica es personal e intransferible y se obliga a mantener el control y custodia de ella y a actuar con máxima diligencia para evitar su utilización no autorizada asumiendo las consecuencias de ello. En consideración a lo anterior, si el Usuario, acepta en las casillas respectivas de tratamiento de datos o términos y condiciones y adquiere productos financieros, los respectivos documentos de vinculación, las citadas casillas de aceptación, así como cada documento que genere, operación que realice firme, valide, ordene o autorice con la citada firma electrónica, será vinculante, sustituye o reemplaza para todos los efectos la firma física del Usuario y tienen todos los efectos jurídicos.\r\n"
			+ "\r\n"
			+ "";
//	
	LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
	DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	@DynamoDBAttribute
	private String dateSave = dateTime.format(dtformat);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getExpeditionDate() {
		return expeditionDate;
	}

	public void setExpeditionDate(String expeditionDate) {
		this.expeditionDate = expeditionDate;
	}

	public Integer getFlagExist() {
		return flagExist;
	}

	public void setFlagExist(Integer flagExist) {
		this.flagExist = flagExist;
	}

	public Integer getCountExperian() {
		return countExperian;
	}

	public void setCountExperian(Integer countExperian) {
		this.countExperian = countExperian;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHabeas() {
		return habeas;
	}

	public void setHabeas(String habeas) {
		this.habeas = habeas;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	
	

	public String getHabeasData() {
		return habeasData;
	}

	public void setHabeasData(String habeasData) {
		this.habeasData = habeasData;
	}

	public String getTycData() {
		return tycData;
	}

	public void setTycData(String tycData) {
		this.tycData = tycData;
	}

	public String getSignatureData() {
		return signatureData;
	}

	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}

	public String getDateSave() {
		return dateSave;
	}

	public void setDateSave(String dateSave) {
		this.dateSave = dateSave;
	}

	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	

	public Integer getFail() {
		return fail;
	}

	public void setFail(Integer fail) {
		this.fail = fail;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStatusFail() {
		return statusFail;
	}

	public void setStatusFail(String statusFail) {
		this.statusFail = statusFail;
	}

	public String getStatusProceed() {
		return statusProceed;
	}

	public void setStatusProceed(String statusProceed) {
		this.statusProceed = statusProceed;
	}

	public Data(String id, String identification, String numberId, String expeditionDate, Integer flagExist,
			Integer countExperian, String names, String lastName, String cellphone, String gender, String email,
			String habeas, String tyc, String firm, Integer fail, String statusFail, String statusProceed, String log,
			Integer count, String habeasData, String tycData, String signatureData, String dateSave) {
		super();
		this.id = id;
		this.identification = identification;
		this.numberId = numberId;
		this.expeditionDate = expeditionDate;
		this.flagExist = flagExist;
		this.countExperian = countExperian;
		this.names = names;
		this.lastName = lastName;
		this.cellphone = cellphone;
		this.gender = gender;
		this.email = email;
		this.habeas = habeas;
		this.tyc = tyc;
		this.firm = firm;
		this.fail = fail;
		this.statusFail = statusFail;
		this.statusProceed = statusProceed;
		this.log = log;
		this.count = count;
		this.habeasData = habeasData;
		this.tycData = tycData;
		this.signatureData = signatureData;
		this.dateSave = dateSave;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"identification\":\"" + identification + "\", \"numberId\":\"" + numberId
				+ "\", \"expeditionDate\":\"" + expeditionDate + "\", \"flagExist\":\"" + flagExist
				+ "\", \"countExperian\":\"" + countExperian + "\", \"names\":\"" + names + "\", \"lastName\":\""
				+ lastName + "\", \"cellphone\":\"" + cellphone + "\", \"gender\":\"" + gender + "\", \"email\":\""
				+ email + "\", \"habeas\":\"" + habeas + "\", \"tyc\":\"" + tyc + "\", \"firm\":\"" + firm
				+ "\", \"fail\":\"" + fail + "\", \"statusFail\":\"" + statusFail + "\", \"statusProceed\":\""
				+ statusProceed + "\", \"log\":\"" + log + "\", \"count\":\"" + count + "\", \"habeasData\":\""
				+ habeasData + "\", \"tycData\":\"" + tycData + "\", \"signatureData\":\"" + signatureData
				+ "\", \"dateSave\":\"" + dateSave + "\"}";
	}

	
	

	
	

	
}
