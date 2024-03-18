package com.softwareit.sduhub.utils

class Constants {
    companion object {
        //        Stories
        const val STORYLY_INSTANCE_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjExNjU1LCJhcHBfaWQiOjE3MzY5LCJpbnNfaWQiOjE5MzU3fQ.ADNtzt-_gErMOjBGhFiNoL7pULvoJOxEI-tR7Q0j9U8"

        const val STORYLY_TRENDS_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjExNjU1LCJhcHBfaWQiOjE3NTg1LCJpbnNfaWQiOjE5NjIyfQ.vwDSxne4cbHiT1qGBz95qQ7EbXtHn8iA3XBwZ29Iv5M"

        //        Room tables
        const val NOTE_TABLE = "notes_table"
        const val IMPORTANT_INFO_TABLE = "important_info_table"

        /**
         * New note identifier, NEW_NOTE_ID = 0, so if any note's id is 0 -> it is new note
         */
        const val NEW_NOTE_ID = 0
    }
}