//snippet-sourcedescription:[ListNamedQueryExample.Kt demonstrates how to obtain a list of named query Id values.]
//snippet-keyword:[AWS SDK for Kotlin]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Athena]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[07/14/2021]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.athena

//snippet-start:[athena.kotlin.ListNamedQueryExample.import]
import aws.sdk.kotlin.runtime.AwsServiceException
import aws.sdk.kotlin.services.athena.AthenaClient
import aws.sdk.kotlin.services.athena.model.ListNamedQueriesRequest
import kotlin.system.exitProcess
//snippet-end:[athena.kotlin.ListNamedQueryExample.import]

/**
To run this Kotlin code example, ensure that you have setup your development environment,
including your credentials.

For information, see this documentation topic:
https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/setup.html
 */

suspend fun main() {

    val athenaClient = AthenaClient { region = "us-west-2" }
    listNamedQueries(athenaClient)
    athenaClient.close()
}

//snippet-start:[athena.kotlin.ListNamedQueryExample.main]
suspend fun listNamedQueries(athenaClient: AthenaClient) {

    try {
        val listNamedQueriesRequest = ListNamedQueriesRequest{
            this.maxResults = 10
        }

        val listNamedQueriesResponses =  athenaClient.listNamedQueries(listNamedQueriesRequest)
        for (listNamedQueriesResponse in listNamedQueriesResponses.namedQueryIds!!) {

            println("The Query ID is $listNamedQueriesResponse")
        }

    } catch (ex: AwsServiceException) {
        println(ex.message)
        athenaClient.close()
        exitProcess(0)
    }
}
//snippet-end:[athena.kotlin.ListNamedQueryExample.main]
