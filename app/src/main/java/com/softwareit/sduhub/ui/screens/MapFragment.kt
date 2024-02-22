package com.softwareit.sduhub.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.theme.SDUHubTheme

class MapFragment : Fragment() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SDUHubTheme {
            Surface {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        MapScreen()
                    }
                }
            }
        }
    }

}

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen() {
    Text(text = "Map Screen")
    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = MapViewportState().apply {
            setCameraOptions {
                zoom(2.0)
                center(Point.fromLngLat(-98.0, 39.5))
                pitch(0.0)
                bearing(0.0)
            }
        },
    )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen()
}
