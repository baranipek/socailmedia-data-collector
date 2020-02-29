package com.social.media.api

import com.social.media.service.SocialMediaService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SocialMediaApi(val socialMediaService: SocialMediaService) {

    @GetMapping(value = ["/"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getSocialMedia() = socialMediaService.getSocialMediaAccounts();

}