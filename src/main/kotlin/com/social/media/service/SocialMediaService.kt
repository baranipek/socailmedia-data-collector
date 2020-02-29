package com.social.media.service

import com.social.media.dto.FaceBook
import com.social.media.dto.Instagram
import com.social.media.dto.SocialAccounts
import com.social.media.dto.Twitter
import com.social.media.exception.FacebookClientException
import com.social.media.exception.InstagramClientException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


@Service
class SocialMediaService {

    var webClient = WebClient.create("https://takehome.io")

    fun getSocialMediaAccounts(): Mono<String> {

        val instagram: Mono<Array<Instagram>> = getInstagram().subscribeOn(Schedulers.parallel())
        val facebook: Mono<Array<FaceBook>> = getFacebook().subscribeOn(Schedulers.parallel())
        val twitter: Mono<Array<Twitter>> = getTwitter().subscribeOn(Schedulers.elastic())


        return Mono.zip(facebook, instagram, twitter).map { tuple ->
            SocialAccounts(
                    tuple.t1, tuple.t2, tuple.t3).toString()
        }

    }
    fun getInstagram(): Mono<Array<Instagram>> {
        return webClient.get()
                .uri("/instagram")
                .retrieve()
                .onStatus({ obj: HttpStatus -> obj.is5xxServerError }
                ) { clientResponse: ClientResponse? -> Mono.error(InstagramClientException("Instagram server error ")) }
                .bodyToMono(Array<Instagram>::class.java)
    }

    fun getFacebook(): Mono<Array<FaceBook>> {

        return webClient.get()
                .uri("/facebook")
                .retrieve()
                .onStatus({ obj: HttpStatus -> obj.is5xxServerError }
                )
                { clientResponse: ClientResponse? -> Mono.just(FacebookClientException("Facebook server error ")) }
                .bodyToMono(Array<FaceBook>::class.java)

    }

    fun getTwitter(): Mono<Array<Twitter>> {
        return webClient.get()
                .uri("/twitter")
                .retrieve()
                .onStatus({ obj: HttpStatus -> obj.is5xxServerError }
                ) { clientResponse: ClientResponse? -> Mono.error(InstagramClientException("Twitter server error ")) }
                .bodyToMono(Array<Twitter>::class.java)

    }

}