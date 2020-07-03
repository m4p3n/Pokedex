package it.mmilani.pokedex.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


fun customAction(id: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): org.hamcrest.Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Action Description"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id) as View
            // your action
        }
    }
}

fun clickItemWithId(id: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Click on a child view with specified id."
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id) as View
            v.performClick()
        }
    }
}


fun withViewAtPosition(
    position: Int,
    itemMatcher: Matcher<View?>
): Matcher<View?>? {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder =
                recyclerView.findViewHolderForAdapterPosition(position)
            return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
        }
    }
}