import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import { createName } from "../bin/cdk";
import * as path from 'path';
import * as iam from "aws-cdk-lib/aws-iam";
import * as fs from 'fs';

export class CdkStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);


    // Creamos un rol para asignarlo a la función lambda
    const lambdaRole = new iam.Role(this, createName("lambda", "role-base-otplogin"), {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com"),
      roleName: createName("lambda", "role-base-otplogin"),
      description: "Rol de IAM para que las funciones lambda puedan ejecutarse.",
    });

    // Añademos Managed Policies al rol de IAM
    lambdaRole.addManagedPolicy(
      iam.ManagedPolicy.fromAwsManagedPolicyName(
        'CloudWatchLambdaInsightsExecutionRolePolicy',
      )
    );
    lambdaRole.addManagedPolicy(
      iam.ManagedPolicy.fromAwsManagedPolicyName(
        'service-role/AWSLambdaBasicExecutionRole',
      )
    );
    lambdaRole.addManagedPolicy(
      iam.ManagedPolicy.fromAwsManagedPolicyName(
        'service-role/AWSLambdaBasicExecutionRole',
      )
    )

    const zipPath = path.join(__dirname, "/../../build/otplogin/otplogin.zip");

    if (!fs.existsSync(zipPath)) {
      console.error("❌ No se encontró el archivo ZIP en:", zipPath);
      throw new Error(`Archivo ZIP no encontrado: ${zipPath}`);
    }

    console.log("✅ Archivo ZIP encontrado en:", zipPath);
    
    /*
    const createclientcreditsLambda = new lambda.Function(this, createName("lambda", "clientcredits"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.clientcredits.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "clientcredits"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/clientcredits/clientcredits.zip")),
      role: lambdaRole,
    });
    */

    const createCoreLambda = new lambda.Function(this, createName("lambda", "Core"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.core.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "Core"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/Core/Core.zip")),
      role: lambdaRole,
    });

    /*
    const createotploginLambda = new lambda.Function(this, createName("lambda", "otplogin"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'co.approbe.otplogin.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "otplogin"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/otplogin/otplogin.zip")),
      role: lambdaRole,
    });
    */

    const createtransactionscoreLambda = new lambda.Function(this, createName("lambda", "transactionscore"), {
      runtime: lambda.Runtime.JAVA_11,
      handler: 'transactions.core.LambdaFunctionHandler::handleRequest',
      functionName: createName("lambda", "transactionscore"),
      code: lambda.Code.fromAsset(path.join(__dirname, "/../../build/transactions.core/transactions.core.zip")),
      role: lambdaRole,
    });

  }
}
