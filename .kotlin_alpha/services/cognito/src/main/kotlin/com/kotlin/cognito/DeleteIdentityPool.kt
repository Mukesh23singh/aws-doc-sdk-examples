//snippet-sourcedescription:[DeleteIdentityPool.kt demonstrates how to delete an existing Amazon Cognito identity pool.]
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

//snippet-start:[cognito.kotlin.deleteidpool.import]
import aws.sdk.kotlin.services.cognitoidentity.CognitoIdentityClient
import aws.sdk.kotlin.services.cognitoidentity.model.CognitoIdentityException
import aws.sdk.kotlin.services.cognitoidentity.model.DeleteIdentityPoolRequest
import kotlin.system.exitProcess
//snippet-end:[cognito.kotlin.deleteidpool.import]


suspend fun main(args: Array<String>){

    val usage = """
        Usage: <identityPoolName>
    
        Where:
            identityPoolName - the name to give your identity pool.
        """

    if (args.size != 1) {
        println(usage)
        exitProcess(0)
    }

    val identityPoold = args[0]
    val cognitoIdentityClient = CognitoIdentityClient { region = "us-east-1" }
    deleteIdPool(cognitoIdentityClient, identityPoold)
    cognitoIdentityClient.close()
}

//snippet-start:[cognito.kotlin.deleteidpool.main]
suspend fun deleteIdPool(cognitoIdclient: CognitoIdentityClient, identityPoold: String?) {

        try {

            val identityPoolInput =  DeleteIdentityPoolRequest {
                this.identityPoolId = identityPoold
            }

            cognitoIdclient.deleteIdentityPool(identityPoolInput)
            println("The identity pool was successfully deleted")

        } catch (ex: CognitoIdentityException) {
            println(ex.message)
            cognitoIdclient.close()
            exitProcess(0)
        }
    }
//snippet-end:[cognito.kotlin.deleteidpool.main]