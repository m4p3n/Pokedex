package it.mmilani.pokedex.ui

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import it.mmilani.pokedex.MainActivity
import it.mmilani.pokedex.R
import it.mmilani.pokedex.api.repo.pokemonModule
import it.mmilani.pokedex.arch.networkModule
import it.mmilani.pokedex.ui.detail.detailModule
import it.mmilani.pokedex.ui.homepage.HomeFragment
import it.mmilani.pokedex.ui.homepage.PokeViewModel
import it.mmilani.pokedex.ui.homepage.adapter.GenericRecylerViewAdapter
import it.mmilani.pokedex.ui.homepage.fragmentModule
import it.mmilani.pokedex.ui.homepage.viewModelModule
import it.mmilani.pokedex.utils.withViewAtPosition
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.inject


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest : KoinTest {

    val viewModel : PokeViewModel by inject()

    @Before
    fun before(){
        stopKoin()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(ApplicationProvider.getApplicationContext())
            modules(listOf(
                networkModule, pokemonModule,  viewModelModule,
                fragmentModule, detailModule
            ))
        }
    }

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun scrollAndClick(){
        activityRule.activity.runOnUiThread {
            val fragment = startHomeFragment()
        }
        onView(withId(R.id.pokemon_list))
            .perform(RecyclerViewActions.scrollToPosition<GenericRecylerViewAdapter.GenericViewHolder<*>>(5))
        onView(withId(R.id.pokemon_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<GenericRecylerViewAdapter.GenericViewHolder<*>>(5, click()))
    }

    @Test
    fun scrollAndCheck(){
        activityRule.activity.runOnUiThread {
            val fragment = startHomeFragment()
        }
        onView(withId(R.id.pokemon_list))
            .perform(RecyclerViewActions.scrollToPosition<GenericRecylerViewAdapter.GenericViewHolder<*>>(15))
        onView(withId(R.id.pokemon_list))
            .check(matches(withViewAtPosition(15, hasDescendant(allOf(withId(R.id.name), isDisplayed())))))
    }

    @After
    fun after(){
        stopKoin()
    }


    fun startHomeFragment(): HomeFragment {
        val activity = activityRule.activity
        val transaction = activity.supportFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        transaction.add(homeFragment, "homeFragment")
        transaction.commit()
        return homeFragment
    }


}