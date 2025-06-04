#!/usr/bin/env node
import * as cdk from 'aws-cdk-lib';
import { CdkStack } from '../lib/cdk-stack';

const app = new cdk.App();


const environment = "dev";

const project = "morris";
const repo = "otplogin";

export function createName(resource: string, functionality: string, caseType: string = "kebab") {
  if (caseType === "camel") {
    function capF(val: string) {
      return String(val).charAt(0).toUpperCase() + String(val).slice(1);
    }
    return `${capF(project)}${capF(resource)}${capF(environment)}${capF(functionality)}`
      .split('-').join('');
  } else if (caseType === "nodash") {
    return `${project}${resource}${environment}${functionality}`;
  } else {
    return `${project}-${resource}-${environment}-${functionality}`;
  }
}

new CdkStack(app, createName(repo, "stack"));