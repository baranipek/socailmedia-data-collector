package com.social.media.handler

import com.social.media.exception.FacebookClientException
import com.social.media.exception.InstagramClientException
import com.social.media.exception.TwitterClientException
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestControllerAdvice
class ErrorHandler {

   private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)

    @ExceptionHandler(TwitterClientException::class)
    internal fun handleTwitterException(ex: TwitterClientException, exception: ServerWebExchange): Mono<String> {
        logger.error("TwitterClientException !" + ex.message)
        return Mono.just("{result:Twitter service is down}")
    }

    @ExceptionHandler(FacebookClientException::class)
    internal fun handleFacebookException(ex: FacebookClientException, exchange: ServerWebExchange): Mono<String> {
        logger.error("FacebookClientException !" + ex.message)

        return Mono.just("{result:Facebook service is down}")
    }

    @ExceptionHandler(InstagramClientException::class)
    internal fun handleInstagramException(ex: InstagramClientException, exchange: ServerWebExchange): Mono<String> {
        logger.error("InstagramClientException !" + ex.message)

        return Mono.just("{result:Instagram service is down}")
    }
}
