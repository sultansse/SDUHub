package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.ui.model.StudentClubDIO
import okhttp3.internal.immutableListOf

class StudentClubsViewModel : BaseViewModel<StudentClubsContract.Event, StudentClubsContract.State, StudentClubsContract.Effect>() {

    override fun setInitialState(): StudentClubsContract.State {
        return StudentClubsContract.State
    }

    override fun handleEvent(event: StudentClubsContract.Event) {
        when (event) {
            is StudentClubsContract.Event.EmptyEffect -> {
                setEffect { StudentClubsContract.Effect.Idle }
            }

            is StudentClubsContract.Event.OnStudentClubClick -> {
                setEffect { StudentClubsContract.Effect.StudentClubDialog(event.studentClub) }
            }

            is StudentClubsContract.Event.OnApplyToClub -> {
                setEffect { StudentClubsContract.Effect.ApplyGoogleForms(event.clubName) }
            }
        }
    }
}


val studentClubs = immutableListOf(
    StudentClubDIO(
        name = "Art Club",
        description = "The Art Club at SDU provides a vibrant community for students interested in exploring and developing their artistic abilities. It offers workshops, guided sessions, and collaborative projects across various mediums such as painting, drawing, and sculpture. The club encourages creative expression and innovation, and organizes art exhibitions to showcase the work of its members.",
        imageResId = R.drawable.img_art_club
    ),
    StudentClubDIO(
        name = "Avangard Volleyball Club",
        description = "Avangard Volleyball Club is ideal for students who are passionate about volleyball and seek to improve their skills while competing at various levels. The club provides structured training, coaching, and regular practice sessions, aiming to enhance teamwork, athletic skills, and strategic thinking.",
        imageResId = R.drawable.img_avangard_flag_club
    ),
    StudentClubDIO(
        name = "Bakers Dozen",
        description = "Bakers Dozen is a vibrant girls' dance club at SDU University, where members embrace the art of dance in its many forms. This club is a hub for students interested in exploring various dance genres including contemporary, hip-hop, ballet, and traditional dances.",
        imageResId = R.drawable.img_bakers_dozen_club
    ),
    StudentClubDIO(
        name = "Dombyra Club",
        description = "Dombyra Club celebrates the rich musical heritage of Kazakhstan by focusing on the traditional string instrument, the dombyra. The club provides lessons for beginners and advanced players, organizes concerts, and participates in cultural festivals to promote Kazakh music.",
        imageResId = R.drawable.img_dombyra_club
    ),
    StudentClubDIO(
        name = "Event Club",
        description = "The Event Club at SDU equips students with the skills necessary to plan, organize, and execute a variety of events. From campus socials to formal galas and academic conferences, members gain hands-on experience in logistics, budgeting, marketing, and event management.",
        imageResId = R.drawable.img_event_club
    ),
    StudentClubDIO(
        name = "Insomnium",
        description = "Insomnium is a tabletop gaming club at SDU University that brings together students who share a passion for board games, card games, and role-playing games (RPGs). The club provides a wide array of gaming options, from classic favorites to the latest releases in the world of tabletop gaming.",
        imageResId = R.drawable.img_insomnium_club
    ),
    StudentClubDIO(
        name = "KingSpeech",
        description = "KingSpeech is dedicated to enhancing the public speaking and debating capabilities of its members. Through regular workshops, mock debates, and participation in national competitions, the club helps students develop confidence, articulation, and critical thinking skills.",
        imageResId = R.drawable.img_king_speech_club
    ),
    StudentClubDIO(
        name = "Language Club",
        description = "The Language Club at SDU fosters a multilingual environment where students can learn and practice new languages. This club supports language acquisition through informal sessions, formal classes, and language exchange meetups, covering languages like English, Russian, Chinese, and more.",
        imageResId = R.drawable.img_language_club
    ),
    StudentClubDIO(
        name = "League of Volunteers",
        description = "This club is dedicated to cultivating a spirit of community service and volunteerism among students. Members participate in a range of activities from local community enhancements to international aid projects.",
        imageResId = R.drawable.img_league_volunteers_club
    ),
    StudentClubDIO(
        name = "Mountain Kings",
        description = "For those who love adventure and the great outdoors, Mountain Kings offers the chance to explore the rugged terrains of Kazakhstan through hiking and mountaineering.",
        imageResId = R.drawable.img_mountain_kings_club
    ),
    StudentClubDIO(
        name = "Music Club",
        description = "The Music Club is a dynamic platform for students interested in performance and music appreciation. It accommodates a variety of musical interests, including instrumental performance, vocal music, and music production.",
        imageResId = R.drawable.img_music_club
    ),
    StudentClubDIO(
        name = "Orlean",
        description = "The Orlean Dance Club at SDU University is a vibrant community dedicated to the exploration and performance of various dance styles. It provides a platform for both novice and experienced dancers to enhance their skills through regular practice sessions and workshops.",
        imageResId = R.drawable.img_orlean_club
    ),
    StudentClubDIO(
        name = "OTAKU",
        description = "OTAKU celebrates Japanese culture, focusing on anime and manga fandom. The club's activities include anime screenings, manga discussions, and cultural events like cosplay and tea ceremonies.",
        imageResId = R.drawable.img_otaku_club
    ),
    StudentClubDIO(
        name = "PandUp",
        description = "PandUp at SDU University is a filmmaking club dedicated to nurturing the cinematic talents of its members. This club provides a comprehensive platform for students interested in all aspects of filmmaking.",
        imageResId = R.drawable.img_pand_up_club
    ),
    StudentClubDIO(
        name = "Panem Debate Club",
        description = "Panem Debate Club promotes intellectual rigor and eloquence, organizing debates on a range of contemporary and philosophical topics. It enhances members' research, public speaking, and critical thinking skills.",
        imageResId = R.drawable.img_panem_club
    ),
    StudentClubDIO(
        name = "Puzzle Club",
        description = "Puzzle Club offers a forum for those who enjoy solving complex puzzles and brain teasers. Activities include puzzle-solving contests, escape room challenges, and logical reasoning workshops.",
        imageResId = R.drawable.img_puzzle_club
    ),
    StudentClubDIO(
        name = "Red Crescent",
        description = "Aligned with the humanitarian principles of the Red Crescent, this club provides first aid training, disaster preparedness sessions, and health campaigns.",
        imageResId = R.drawable.img_red_crescent_club
    ),
    StudentClubDIO(
        name = "Sana IQ Club",
        description = "Sana IQ Club challenges its members with intellectual games and quiz-based competitions. It promotes a broad knowledge base and quick thinking, preparing members for quiz competitions.",
        imageResId = R.drawable.img_sana_iq_club
    ),
    StudentClubDIO(
        name = "SDU Book Club",
        description = "The SDU Book Club fosters a love for reading and literary discussion among students. Members explore various genres and authors, participate in book exchanges, and engage with guest authors.",
        imageResId = R.drawable.img_sdu_book_club
    ),
    StudentClubDIO(
        name = "Shapagat",
        description = "Shapagat is committed to charitable activities and social outreach, providing support to underprivileged communities through various initiatives.",
        imageResId = R.drawable.img_shapagat_club
    ),
    StudentClubDIO(
        name = "SMMelo",
        description = "Specializing in social media marketing, SMMelo educates members on the effective use of social platforms for branding and communication.",
        imageResId = R.drawable.img_smm_club
    ),
    StudentClubDIO(
        name = "StandUp",
        description = "StandUp at SDU University is more than just a club; it's a school of humor where students can hone their comedic skills. This unique platform allows members to explore various aspects of comedy.",
        imageResId = R.drawable.img_stand_up_club
    ),
    StudentClubDIO(
        name = "Unesco",
        description = "Linked to UNESCO's global mission, this club engages in projects related to education, science, culture, and sustainable development.",
        imageResId = R.drawable.img_unesco_club
    ),
    StudentClubDIO(
        name = "Vision",
        description = "Vision at SDU University is a multifaceted club that focuses on leadership and personal development, also organizing charitable events and fairs.",
        imageResId = R.drawable.img_vision_club
    ),
    StudentClubDIO(
        name = "WildCats SDU",
        description = "Representing SDU in a variety of sports, WildCats SDU promotes athleticism and team spirit. The club participates in regional and national sports competitions.",
        imageResId = R.drawable.img_wild_cats_club
    ),
    StudentClubDIO(
        name = "Zamana",
        description = "Zamana at SDU University is a history club that serves as a nexus for students passionate about exploring the past and understanding its impact on the present and future.",
        imageResId = R.drawable.img_zamana_club
    ),
    StudentClubDIO(
        name = "Zhasa Club",
        description = "Zhasa Club at SDU University is a creative hub designed to foster innovation and artistic expression among students. It organizes workshops, collaborative projects, and exhibitions.",
        imageResId = R.drawable.img_zhasa_club
    )
)
