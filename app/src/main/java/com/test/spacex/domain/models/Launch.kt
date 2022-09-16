package com.test.spacex.domain.models

data class Launch(
    val flight_number : Int? = null,
    val mission_name : String? = null,
    val rocket_name : String? = null,
    val launch_site_name : String? = null,
    val date_of_launch : String? = null,
    val launch_patch_image : String? = null,
    var isSelected : Boolean = false
)