// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetEventSelectors.kt demonstrates how to get event selectors for a given trail.]
//snippet-keyword:[AWS SDK for Kotlin]
// snippet-service:[AWS CloudTrail]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[06/02/2021]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.cloudtrail

//snippet-start:[cloudtrail.kotlin.get_event_selectors.import]
import aws.sdk.kotlin.services.cloudtrail.CloudTrailClient
import aws.sdk.kotlin.services.cloudtrail.model.GetEventSelectorsRequest
import aws.sdk.kotlin.services.cloudtrail.model.CloudTrailException
import kotlin.system.exitProcess
//snippet-end:[cloudtrail.kotlin.get_event_selectors.import]

/**
To run this Kotlin code example, ensure that you have setup your development environment,
including your credentials.

For information, see this documentation topic:
https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/setup.html
 */

suspend fun main(args: Array<String>) {

    val usage = """

    Usage:
        <trailName>  

    Where:
        trailName - the name of the trail. 
      
    """

    if (args.size != 1) {
        println(usage)
        exitProcess(0)
     }

    val trailName = args.get(0)
    val cloudTrailClient = CloudTrailClient{ region = "us-east-1" }
    getSelectors(cloudTrailClient, trailName)
    cloudTrailClient.close()
}

    //snippet-start:[cloudtrail.kotlin.get_event_selectors.main]
    suspend  fun getSelectors(cloudTrailClient: CloudTrailClient, trailNameVal: String) {
        try {

            val selectorsRequest = GetEventSelectorsRequest {
                trailName =trailNameVal
            }

            val selectorsResponse = cloudTrailClient.getEventSelectors(selectorsRequest)
            val selectors = selectorsResponse.eventSelectors

            if (selectors != null) {
                for (selector in selectors) {
                    println("The type is ${selector.readWriteType.toString()}")
                }
            }

        } catch (ex: CloudTrailException) {
            println(ex.message)
            cloudTrailClient.close()
            exitProcess(0)
        }
 }
//snippet-end:[cloudtrail.kotlin.get_event_selectors.main]
