//snippet-sourcedescription:[DeleteUserPool.kt demonstrates how to delete an existing user pool.]
//snippet-keyword:[AWS SDK for Kotlin]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Cognito]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[08/01/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.cognito

//snippet-start:[cognito.kotlin.DeleteUserPool.import]
import aws.sdk.kotlin.services.cognitoidentityprovider.CognitoIdentityProviderClient
import aws.sdk.kotlin.services.cognitoidentityprovider.model.DeleteUserPoolRequest
import aws.sdk.kotlin.services.cognitoidentity.model.CognitoIdentityException
import kotlin.system.exitProcess
//snippet-end:[cognito.kotlin.DeleteUserPool.import]


suspend fun main(args: Array<String>){

    val usage = """
        Usage: <userPoolId>
    
        Where:
            userPoolId - the Id value given to your user pool.
        """

      if (args.size != 1) {
          println(usage)
          exitProcess(0)
     }

    val userPoolId= args[0]
    val cognitoClient = CognitoIdentityProviderClient { region = "us-east-1" }
    delPool(cognitoClient,userPoolId)
    cognitoClient.close()
}

//snippet-start:[cognito.kotlin.DeleteUserPool.main]
suspend fun delPool(cognitoClient: CognitoIdentityProviderClient, userPoolId:String) {

        try {

            val deleteUserPoolRequest =  DeleteUserPoolRequest{
                 this.userPoolId = userPoolId
            }

            cognitoClient.deleteUserPool(deleteUserPoolRequest)
          print("$userPoolId was successfully deleted")

        } catch (ex: CognitoIdentityException) {
            println(ex.message)
            cognitoClient.close()
            exitProcess(0)
        }
  }
//snippet-end:[cognito.kotlin.DeleteUserPool.main]