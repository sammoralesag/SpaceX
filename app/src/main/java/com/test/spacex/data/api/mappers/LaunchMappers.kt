package com.test.spacex.data.api.mappers

import com.test.spacex.data.dto.launch.LaunchItem
import com.test.spacex.domain.models.Launch

fun LaunchItem.toLaunch() : Launch {
    return Launch(this.flight_number, this.mission_name, this.rocket?.rocket_name, this.launch_site?.site_name, this.launch_date_utc, this.links?.mission_patch)
}