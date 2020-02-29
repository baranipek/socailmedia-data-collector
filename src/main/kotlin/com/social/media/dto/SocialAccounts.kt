package com.social.media.dto

data class SocialAccounts(val faceBook: Array<FaceBook>, val instagram: Array<Instagram>, val twitter:
Array<Twitter>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SocialAccounts

        if (!faceBook.contentEquals(other.faceBook)) return false
        if (!instagram.contentEquals(other.instagram)) return false
        if (!twitter.contentEquals(other.twitter)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = faceBook.contentHashCode()
        result = 31 * result + instagram.contentHashCode()
        result = 31 * result + twitter.contentHashCode()
        return result
    }

    override fun toString(): String {
        var result = "{ result:{"

        if (instagram.isNotEmpty()) {
            result += "twitter: ["
            for (element in instagram) {
                result += " " + element.picture
                result += ","
            }
            result += "],"
        }

        if (faceBook.isNotEmpty()) {
            result += "facebook: ["
            for (element in faceBook) {
                result += " " + element.status
                result += ","
            }
            result += "],"
        }

        if (twitter.isNotEmpty()) {
            result += "twitter: ["
            for (element in twitter) {
                result += " " + element.tweet
                result += ","
            }
            result += "]"
        }
        result += "}"

        return result
    }
}