import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import { createName } from "../bin/cdk";
import * as path from 'path';
import * as iam from "aws-cdk-lib/aws-iam";
import * as fs from 'fs';
import { Stack } from 'aws-cdk-lib';

export class CdkStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);


    // Creamos un rol para asignarlo a la función lambda
    const lambdaRole = new iam.Role(this, createName("lambda", "role-base-otplogin"), {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com"),
      roleName: createName("lambda", "role-base-otplogin"),
      description: "Rol de IAM para que las funciones lambda puedan ejecutarse.",
    });

    // Politicas al rol creado
    const awsManagedPolicies = [
      'service-role/AWSLambdaBasicExecutionRole',
      'CloudWatchLambdaInsightsExecutionRolePolicy',
      'AmazonDynamoDBFullAccess',
      'AmazonMQApiFullAccess',
      'AmazonMQFullAccess',
      'AmazonCognitoDeveloperAuthenticatedIdentities',
      'AmazonCognitoPowerUser',
      'SecretsManagerReadWrite'
    ];
    
    for (const name of awsManagedPolicies) {
      lambdaRole.addManagedPolicy(
        iam.ManagedPolicy.fromAwsManagedPolicyName(name)
      );
    }
    
    // Políticas Customer Managed
    const customerManagedPolicies = [
      'AWSLambdaBasicExecutionRole-efead4fd-cd7f-4f25-ad4b-7857a21fcd52',
      'lambdaInvocation',
      'AWSLambdaBasicExecutionRole-c8258075-1288-461d-b4c5-5217ba2ebb3b', 
      'lambdaInvoke' 
    ];
    
    for (const name of customerManagedPolicies) {
      const arn = Stack.of(this).formatArn({
        service: 'iam',
        resource: 'policy',
        resourceName: name,
        region: '', 
      });
    
      lambdaRole.addManagedPolicy(
        iam.ManagedPolicy.fromManagedPolicyArn(this, `${name}Policy`, arn)
      );
    }
    
    /*
    const zipPath = path.join(__dirname, "/../../build/otplogin/otplogin.zip");

    if (!fs.existsSync(zipPath)) {
      console.error("❌ No se encontró el archivo ZIP en:", zipPath);
      throw new Error(`Archivo ZIP no encontrado: ${zipPath}`);
    }

    console.log("✅ Archivo ZIP encontrado en:", zipPath);
    
    const createclientcreditsLambda = new lambda.Function(this, createName("lambda", "clientcredits"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.clientcredits.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "clientcredits"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/clientcredits/clientcredits.zip")),
      role: lambdaRole,
      environment: {
        AUDIENCE: "https://sandbox.lms.kordev.io",
        CLIENT_ID: "twMV6kgIwJItbGgHlPUMQTpTfNGgngcD",
        DB_PASSWORD: "ApprobeTest1+",
        DB_URL: "jdbc:mysql://34.150.132.109:3306/cashcollection",
        DB_USER: "aws",
        JAVA_TOOL_OPTIONS: "-XX:+TieredCompilation -XX:TieredStopAtLevel=1",
        SECRET_ID: "F4Vyj_10cNoOr1Mw2A2DSUCTVZJhdWFqLOuKgUJPS6ruRJM65c8S6ZKW2YYV_JTi",
        URL_CORE: "https://sandbox.lms.kordev.io/",
        URL_CORE_TOKEN: "https://auth.kordev.io"
      }
    });    

    const createCoreLambda = new lambda.Function(this, createName("lambda", "Core"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.core.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "Core"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/Core/Core.zip")),
      role: lambdaRole,
      environment: {
        AUDIENCE: "https://sandbox.lms.kordev.io",
        CLIENT_ID: "twMV6kgIwJItbGgHlPUMQTpTfNGgngcD",
        JAVA_TOOL_OPTIONS: "-XX:+TieredCompilation -XX:TieredStopAtLevel=1",
        SECRET_ID: "F4Vyj_10cNoOr1Mw2A2DSUCTVZJhdWFqLOuKgUJPS6ruRJM65c8S6ZKW2YYV_JTi",
        URL_CORE: "https://sandbox.lms.kordev.io/",
        URL_CORE_TOKEN: "https://auth.kordev.io"
      }
    });
    
    const createotploginLambda = new lambda.Function(this, createName("lambda", "otplogin"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.otplogin.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "otplogin"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/otplogin/otplogin.zip")),
      role: lambdaRole,
      environment: {
        AUDIENCE: "https://sandbox.lms.kordev.io",
        AUTH_PASS: "a2Vubnkuc2lsdmFAYXBwcm9iZS5jb3xBcHByb2JlIC0gRHV2ZXJhUUF8OTAxNDk5MDM2NXxUaHUgSnVuIDE1IDE5OjQ4OjE2IFVUQyAyMDIzfFFVQjNqdy91RHBBbnk3bUs3TVo4Y2c9PXw",
        CLIENT_ID: "twMV6kgIwJItbGgHlPUMQTpTfNGgngcD",
        CLIENT_ID_COGNITO: "2chkv23igopebaago269kbhc60",
        EMAIL: "Sebastian.Timana@approbe.co",
        JAVA_TOOL_OPTIONS: "-XX:+TieredCompilation -XX:TieredStopAtLevel=1",
        PASS_EMAIL: "SEBASTIANgomez22@",
        POOL_ID: "us-east-1_wjgyWp8uE",
        SECRET_ID: "F4Vyj_10cNoOr1Mw2A2DSUCTVZJhdWFqLOuKgUJPS6ruRJM65c8S6ZKW2YYV_JTi",
        TYPE_OTP: "3",
        URL_AWS: "https://vuwnwhdth3.execute-api.us-east-1.amazonaws.com/ApprobeDev/",
        URL_CORE: "https://sandbox.lms.kordev.io/",
        URL_CORE_TOKEN: "https://auth.kordev.io",
        URL_GCP: "https://test.approbe.co/",
        URL_OTP: "https://j4dmk2yez8.execute-api.us-east-1.amazonaws.com/test"
      }
    });    
    */

    const createtransactionscoreLambda = new lambda.Function(this, createName("lambda", "transactionscore"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'transactions.core.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "transactionscore"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/transactions.core/transactions.core.zip")),
      role: lambdaRole,
      environment: {
        QUEUE_NAME: "core-queue"
      }
    });

  }
}
