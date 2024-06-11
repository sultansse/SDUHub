package com.softwareit.sduhub.ui.screens.home_screen.categories.services.alumni_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.R
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Parcelize
class AlumniScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen
        val viewModel: AlumniViewModel = koinViewModel()

        val uiState: State<AlumniContract.State> = viewModel.uiState.collectAsState()
        val onUiEvent: (AlumniContract.Event) -> Unit = { viewModel.setEvent(it) }
        val uiEffect: State<AlumniContract.Effect> =
            viewModel.effect.collectAsState(initial = AlumniContract.Effect.Idle)


        when (val effect = uiEffect.value) {
            is AlumniContract.Effect.Idle -> Unit
            is AlumniContract.Effect.AlumniBottomSheet -> {
                val item = effect.alumni
                ModalBottomSheet(
                    onDismissRequest = { onUiEvent(AlumniContract.Event.EmptyEffect) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {

                        item {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = item.image,
                                    contentDescription = null,
                                    imageLoader = koinInject()
                                )
                            }
                        }

                        item {
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Position: ")
                                }
                                append("${item.position}")
                            })
                        }



                        item {
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Language: ")
                                }
                                append("${item.language}")
                            })
                        }
                        item {
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Profession: ")
                                }
                                append("${item.profession}")
                            })
                        }
                        item {
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Education: ")
                                }
                                append("${item.education}")
                            })
                        }
                        item.workPlace?.let {
                            item {

                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Work place: ")
                                    }
                                    append("$it")
                                })
                            }
                        }
                        item.workExperience?.let {
                            item {

                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Work experience: ")
                                    }
                                    append("$it")
                                })
                            }
                        }
                        item.awards?.let {
                            item {
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Awards: ")
                                    }
                                    append("$it")
                                })
                            }
                        }
                        item.quotes?.let {
                            item {
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Quotes: ")
                                    }
                                    append("\"$it\"")
                                })
                            }
                        }
                    }
                }
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Alumni") },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                AlumniScreen()
            }
        }
    }

    @Composable
    fun AlumniScreen() {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(listOfAlumni.size) { index ->
                AlumniItem(listOfAlumni[index])
            }
        }
    }
}

@Composable
private fun AlumniItem(item: AlumniDTO) {
    val viewModel: AlumniViewModel = koinViewModel()

    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .clickable { viewModel.setEvent(AlumniContract.Event.OnAlumniDetailsClick(item)) }
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                imageLoader = koinInject(),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = item.fullname,
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

val listOfAlumni = ImmutableList.of(
    AlumniDTO(
        id = 0,
        fullname = "Bagdat Mussin",
        image = R.drawable.bagdat_mussin,
        birthPlace = null,
        position = "Minister of Digital Development, Innovation and Aerospace Industry of the Republic of Kazakhstan",
        language = "Kazakh, English, Russian, Turkish",
        profession = "IT Specialist, Innovator, Entrepreneur",
        education = "Computer Science, SDU University, 2000-2004, Lawyer, Kazakh Institute of Law and International Relations, 2004-2006.",
        workPlace = "Chief software engineer, department head, deputy director of the department for the creation of IT units in state bodies of JSC “National Information Technologies”, 2004-2007, Head of Information Systems Support Department at RSE “Republican Center for Legal Information” of the Ministry of Justice of the Republic of Kazakhstan, 2007-2008,  Advisor to the Minister of Justice of the Republic of Kazakhstan on Information Technologies, Head of the Department of Information Technologies of the Ministry of Justice of the Republic of Kazakhstan, 2008-2010,  Deputy Director of the Department of State Policy in the Field of Information Technologies of the Ministry of Communications and Information of the Republic of Kazakhstan, 2010-2011, Deputy Chairman of the Committee for Monitoring Automation of Public Services and Coordinating the Activities of Public Service Centers of the Ministry of Communications and Information of the Republic of Kazakhstan, 2011-2012, Chairman of the Committee for Control of Automation of Public Services and Coordination of Activities of Public Service Centers of the Ministry of Transport and Communications of the Republic of Kazakhstan, 2012-2014, Chairman of the Board of the National Information Technologies Joint Stock Company, 04-08.2014, Chairman of the Board of Kazpost JSC, 2014-2017, Chairman of the Committee on Legal Statistics and Special Accounts of the General Prosecutor’s Office of the Republic of Kazakhstan, 2017-2018, Deputy Chairman of the Board of BI Group and Head of BeInTech, 2018-2019.",
        workExperience = null,
        awards = "Medals: «Ерен еңбегі үшін» and «20 years of Independence of the Republic of Kazakhstan»",
        quotes = "The «Bolashak» program itself is aimed at generating an intelligent future generation, but if it is an intelligent future generation, having studied at American top universities, comes here and settles in our government, where everyone breaks it down, sets the framework and so on, I think , this is of little use. They need to be let go so that they work abroad, but they will bring at least some idea from there or organize a venture fund that will help us or will be an example for other Kazakhstanis.",
    ),
    AlumniDTO(
        id = 1,
        fullname = "Alisher Utev",
        image = R.drawable.alisher_alumni,
        birthPlace = null,
        position = "Filmmaker",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Education",
        education = "Two Foreign Language, SDU University, 2006-2010",
        workPlace = "Executive producer of the TV show “Tungi Studiya Nurlan Koyanbaev”, TV channel “Kazakhstan”",
        workExperience = "Script writer, Executive producer, Actor, «Бизнес по-казахски».  2016, Script writer, Executive producer, Actor, «Брат или Брак» 2017, Script writer, «Сиситай» 2018, Script writer, Executive producer, Actor, «Каникулы в Таиланде» 2018, Script writer, Executive producer, «Брат или Брак 2» 2019.",
        awards = "«Выбор кинокритиков» – Лучший сценарий, Лучший фильм «Бизнес по-казахски»",
        quotes = "The university gave me a very high-quality impetus. When I entered the university in 2006, it was popular, let’s say, in a narrow circle. In general, for me, my university has done a lot, and I believe that it was the university that played a big role in my development as a person.",
    ),
    AlumniDTO(
        id = 2,
        fullname = "Almas Zhali",
        image = R.drawable.zhali_alumni,
        birthPlace = null,
        position = "Lawyer",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Finance and accounting expert",
        education = "Jurisprudence, SDU University, 2012-2016",
        workPlace = "Lawyer, White and Case LLP, 2015.",
        workExperience = null,
        awards = null,
        quotes = "I do not want to give empty promises, because I am always responsible for my words",
    ),
    AlumniDTO(
        id = 3,
        fullname = "Indira Niyazali",
        image = R.drawable.indira_alumni,
        birthPlace = null,
        position = "Strategic Business Partner at Government of Abu Dhabi",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Business Strategy, Sales and Marketing Strategy, Net Business Development",
        education = "Economics and Management, SDU University, 2002-206",
        workPlace = "Managing Banking Products and Services, «3i-infotech», 2006-2008, Regional Manager, Bahwan Cybertack, 2009-2010, Business Relationship Manager, C4 Advanced Solution, 2010-2012, Territory Cloud Sales Manager, Orcale, 2013-2015, Cloud Sales Manager, Orcale, 2015-2019, Account Management Organization Counter Leader for Gulf/Turkey/Levant, 2019-2020, Strategic Business Partner at Government of Abu Dhabi, 2020",
        workExperience = null,
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 4,
        fullname = "Yelena Smirnova",
        image = R.drawable.yelena_alumni,
        birthPlace = null,
        position = "Dean at GBSB Business School",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Education",
        education = "Economics, SDU University, 2003-2007, Management (Master’s Degree), SDU University, 2007-2009, Entrepreneurship (MBA), Kingston University, 2009-2012, Management (PhD), SDU University.",
        workPlace = "Marketing Manager, Embassy of India in the Republic of Kazakhstan, 2007-2007, Visiting Lecturer, Narxoz University, 2011-2012, Senior Lecturer, SDU University, 2011-2014, Chair of Scientific Bureau of Management & Marketing Department, Faculty of Economics, SDU University, 2014-2016, Vice-Director at the Institute of Economic, Social and Business Studies (IESBS), SDU University, 2014-2016, Senior Researcher at the Institute of Economic, Social and Business Studies (IESBS), SDU University, 2015-2018, Associate Professor, SDU University, 2016-2020, Senior Academic Coordinator / Head of Academics, GBSB Business School, 2016-2017, Dean, GBSB Business School, 2017-",
        workExperience = null,
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 5,
        fullname = "Azamat Ospanov",
        image = R.drawable.aza_alumni,
        birthPlace = null,
        position = "CEO of USTUDY Education Centre",
        language = "Kazakh, English, Russian, Turkish",
        profession = "IT Specialist, Entrepreneur, Innovator",
        education = "Economics, SDU University, 2006-2010",
        workPlace = "Ministry of Agriculture of Kazakhstan, 2003-2003, Merge and Acquisition Specialist, Food Empire, 2003-2010, Founder and CEO, USTUDY, 2009",
        workExperience = null,
        awards = null,
        quotes = "For most, education is just a piece of paper, not an opportunity to get better. A hundred years ago, there were figures who raised our educational system – Alikhan Bukeikhanov, Mirzhakyp Dulatov, Akhmet Baitursynov, Temirbek Zhurgenev and others. We need new heroes.",
    ),
    AlumniDTO(
        id = 6,
        fullname = "Amirkhan Omarov",
        image = R.drawable.amir_alumni,
        birthPlace = null,
        position = "Finance and accounting expert",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Finance and accounting expert",
        education = "International Economics, SDU University, 2000-2004",
        workPlace = "Auditor, Ernst & Young, 2003-2004, Manager on audit, KPMG, 2004-2009, Senior Finance Manager, Air Astana, 2009-2012, Director Engineering Finance and Materials Management, 2013-2017, Vice-President Finance Accounts and Chief Accountant, Air Astana, 2017",
        workExperience = "For me, the representative of the Millennium generation, the idea itself is important to be a part of the development of the national aviation industry and the creation of a high-quality and reliable national brand. My suggestion for young generation, do not afraid of trying new things, it will not be end of your life. 2015. I do not want to give empty promises, because I am always responsible for my words.",
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 7,
        fullname = "Zhanat Rakhmani",
        image = R.drawable.zhanat_alumni,
        birthPlace = null,
        position = "CEO of USTUDY Education Centre",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Education, Economy, Business, Innovation",
        education = "Economics, SDU University, 2006-2010",
        workPlace = "Ministry of Agriculture of Kazakhstan, 2003-2003, Merge and Acquisition Specialist, Food Empire, 2003-2010, Founder and CEO, USTUDY, 2009",
        workExperience = null,
        awards = null,
        quotes = "For most, education is just a piece of paper, not an opportunity to get better. A hundred years ago, there were figures who raised our educational system – Alikhan Bukeikhanov, Mirzhakyp Dulatov, Akhmet Baitursynov, Temirbek Zhurgenev and others. We need new heroes.",
    ),
    AlumniDTO(
        id = 8,
        fullname = "Amirkhan Omarov",
        image = R.drawable.amir_alumni,
        birthPlace = null,
        position = "Finance and accounting expert",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Finance and accounting expert",
        education = "International Economics, SDU University, 2000-2004",

        workPlace = "Auditor, Ernst & Young, 2003-2004, Manager on audit, KPMG, 2004-2009, Senior Finance Manager, Air Astana, 2009-2012, Director Engineering Finance and Materials Management, 2013-2017, Vice-President Finance Accounts and Chief Accountant, Air Astana, 2017",
        workExperience = "For me, the representative of the Millennium generation, the idea itself is important to be a part of the development of the national aviation industry and the creation of a high-quality and reliable national brand. My suggestion for young generation, do not afraid of trying new things, it will not be end of your life. 2015. I do not want to give empty promises, because I am always responsible for my words.",
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 9,
        fullname = "Mirus Kurmashev",
        image = R.drawable.mirus_alumni,
        birthPlace = null,
        position = "IT Specialist, Entrepreneur, Innovator",
        language = "Kazakh, English, Russian, Turkish",
        profession = "IT Specialist, Entrepreneur, Innovator",
        education = "Computer Science, SDU University, 2003-2007",
        workPlace = "Chairman of the IT-Council, Atameken RPC. Founder and CEO of MIRUSDESK, 2015",
        workExperience = "If something happens in a person’s life that he cannot change, or that with which he does not agree, then the only thing he can work on is on himself. I am focused on the inner state of my soul. If a person is not at odds and not in harmony with the soul, then all the imbalance around him occurs precisely for this reason. In order to influence your life and environment, you must first influence yourself.",
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 10,
        fullname = "Nariman Mukushev",
        image = R.drawable.nariman_alumni,
        birthPlace = null,
        position = "IT Specialist",
        language = "Kazakh, English, Russian, Turkish",
        profession = "IT Specialist",
        education = "Computer Science, SDU University, 2003-2007, Business Administration, International Academy of Business, 2009-2011.",
        workPlace = "Specialist, leading specialist, head of department, deputy head of department, head of department, director of the department of innovative technologies, Kazkommertsbank JSC, 2005-2014, Managing Director for Innovation and IT, Kazpost JSC, 2014-2017, Managing Director for Business and IT Transformation, Kazpost JSC, 2017-2018, Vice Minister of Labor and Social Protection of the Republic of Kazakhstan, 2018-2020, IT Specialist, BI Innovation.",
        workExperience = "Be sure to listen to the opinions and ideas of employees. It doesn’t matter what his position or age is, you should always listen, because we do teamwork. If you have good ideas and projects, you need to launch and develop them. First of all, a person needs to be interested so that he is interested, because the ultimate goal is the result. For me, this was always an incentive when I saw the ultimate goal and understood why I was doing this.",
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 11,
        fullname = "Askhat Omarov",
        image = R.drawable.askhat_alumni,
        birthPlace = null,
        position = "IT specialist, Businessman, Innovator, Entrepreneur",
        language = "Kazakh, English, Russian, Turkish",
        profession = "IT specialist, Businessman, Innovator, Entrepreneur",
        education = "Computer Science, SDU University, 2003-2008",
        workPlace = "System engineer, Asia Intercommunications, 2008-2009, Owner and CEO of Asia Intercommunications, 2010-2013, Founder and CEO of Santufei.kz, Santufei, 2013-2020, Founder of Farel ( Part of Y-Combinator), 2020.",
        workExperience = "There is nothing supernatural. You need to do the business that you like, and thoroughly study and understand its features. Persistence will be required in order not to give up when something fails. The main thing is to understand, to delve into the details, in a word, to become competent in the chosen field. Only then can you make a successful project or business in it. Without understanding the essence, it will not work to achieve good results.",
        awards = null,
        quotes = null,
    ),
    AlumniDTO(
        id = 12,
        fullname = "Pavel Koktyshev",
        image = R.drawable.pavel_alumni,
        birthPlace = null,
        position = "Deputy Chairman of the Management Board of JSC National Infocommunication Holding Zerde",
        language = "Kazakh, English, Russian, Turkish",
        profession = "Entrepreneur, Venture capitalist, Public figure",
        education = "International Relations, SDU University, 2008-2012, International Trade, KIMEP University, 2012-2014",
        workPlace = "Vice-President, Kazakhstan Green Party, 2009-2012, News Anchor, Kazakh TV, 2010-Country Host, Global Entrepreneurship Week in Kazakhstan, 2011 –Director, Institute for Development and Economic Affairs, 2011 – Co-Founder, Ceo, Most Young Entrepreneurs Club, 2013 – Deputy Chairman of the board, Zerde National Infocommunication Holding",
        workExperience = "The uniqueness of IT companies is that they do not just receive benefits, they reinvest in the development of the country.",
        awards = null,
        quotes = null,
    ),
)

