// snippet-sourcedescription:[DetectKeyPhrases.kt demonstrates how to detect key phrases.]
//snippet-keyword:[AWS SDK for Kotlin]
// snippet-service:[Amazon Comprehend]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[03/04/2021]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.comprehend

//snippet-start:[comprehend.kotlin.detect_keyphrases.import]
import aws.sdk.kotlin.services.comprehend.ComprehendClient
import aws.sdk.kotlin.services.comprehend.model.DetectKeyPhrasesRequest
import aws.sdk.kotlin.services.comprehend.model.LanguageCode
import aws.sdk.kotlin.services.comprehend.model.ComprehendException
import kotlin.system.exitProcess
//snippet-end:[comprehend.kotlin.detect_keyphrases.import]

suspend fun main() {

    val comprehendClient = ComprehendClient({region="us-east-1"})
    val text = "Amazon.com, Inc. is located in Seattle, WA and was founded July 5th, 1994 by Jeff Bezos, allowing customers to buy everything from books to blenders. Seattle is north of Portland and south of Vancouver, BC. Other notable Seattle - based companies are Starbucks and Boeing."
    detectAllKeyPhrases(comprehendClient,text)
    comprehendClient.close()
}

//snippet-start:[comprehend.kotlin.detect_keyphrases.main]
suspend fun detectAllKeyPhrases(comClient: ComprehendClient, textVal: String) {
        try {
            val detectKeyPhrasesRequest = DetectKeyPhrasesRequest {
                 text = textVal
                 languageCode = LanguageCode.fromValue("en")
            }
            val resp = comClient.detectKeyPhrases(detectKeyPhrasesRequest)
            val phraseList = resp.keyPhrases
            if (phraseList != null) {
                for (phrase in phraseList) {
                    println("Key phrase text is ${phrase.text}")
                }
            }

        } catch (ex: ComprehendException) {
            println(ex.message)
            exitProcess(0)
        }
}
//snippet-end:[comprehend.kotlin.detect_keyphrases.main]