package com.softwareit.sduhub.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.softwareit.sduhub.R
import com.softwareit.sduhub.base.BaseFragment


class MapFragment : BaseFragment() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SetContent() {
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

    @Composable
    fun MapScreen() {
        val LONGITUDE = 76.66967
        val LATITUDE = 43.207176

        val context = LocalContext.current
        val mapView = MapView(context)
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { mapView ->
            mapView.mapboxMap
                .apply {
                    loadStyle(Style.MAPBOX_STREETS)
                    setCamera(
                        CameraOptions.Builder()
                            .center(Point.fromLngLat(LONGITUDE, LATITUDE))
                            .zoom(16.0)
                            .build()
                    )
                }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun MapScreenPreview() {
        SetContent()
    }
}