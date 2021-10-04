//snippet-sourcedescription:[ListShards.kt demonstrates how to list the shards in an Amazon Kinesis data stream.]
//snippet-keyword:[AWS SDK for Kotlin
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Kinesis]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[06/07/2021]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.kinesis

//snippet-start:[kinesis.kotlin.ListShards.import]
import aws.sdk.kotlin.services.kinesis.KinesisClient
import aws.sdk.kotlin.services.kinesis.model.KinesisException
import aws.sdk.kotlin.services.kinesis.model.ListShardsRequest
import kotlin.system.exitProcess
//snippet-end:[kinesis.kotlin.ListShards.import]

/**
To run this Kotlin code example, ensure that you have setup your development environment,
including your credentials.

For information, see this documentation topic:
https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/setup.html
 */

suspend fun main(args: Array<String>){

    val usage = """
    Usage: <streamName>

    Where:
        streamName - The Amazon Kinesis data stream (for example, StockTradeStream)
    """

    if (args.size != 1) {
        println(usage)
        exitProcess(0)
    }

    val name: String = args[0]
    val kinesisClient = KinesisClient{region ="us-east-1"}
    listKinShards(kinesisClient, name)
    kinesisClient.close()
}

//snippet-start:[kinesis.kotlin.ListShards.main]
suspend fun listKinShards(kinesisClient: KinesisClient, name: String?) {
    try {
        val request = ListShardsRequest {
            streamName = name
        }
        val response = kinesisClient.listShards(request)
        val myIt = response.shards?.iterator()

        var shardCount = 0
        if (myIt != null) {
            while (myIt.hasNext()) {
                shardCount++
                myIt.next()
            }
        }

        println("${request.streamName} has $shardCount shards.")

    } catch (e: KinesisException) {
        println(e.message)
        kinesisClient.close()
        exitProcess(0)
    }
    println("Done")
}
//snippet-end:[kinesis.kotlin.ListShards.main]