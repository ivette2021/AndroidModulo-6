package com.example.centrodeformacionfuturo.Modelo

import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesDetailEntity
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesEntity
import com.example.centrodeformacionfuturo.Modelo.remote.frominternet.CourseDetail
import com.example.centrodeformacionfuturo.Modelo.remote.frominternet.Courses

fun fromInternetCoursesEntity( coursesList: List<Courses>) :List<CoursesEntity>{

    return coursesList.map {//aca lo que llegue de internet se lo pasaremos a la parte local
        CoursesEntity( //hay que pasarle todos los datos a la local debido a que remotas y local deben tener todos los campos ocupados
            id=it.id,
            title= it.title,
            previewDescription = it.previewDescription,
            image = it.image,
            weeks = it.weeks,
            star= it.start
        )
    }
}

fun fromInternetCourseDetailEntity( course: CourseDetail) : CoursesDetailEntity {

    return CoursesDetailEntity(
        id=course.id,
        title= course.title,
        previewDescription = course.previewDescription,
        image = course.image,
        weeks = course.weeks,
        tuition = course.tuition,
        minimumSkill = course.minimumSkill,
        scholarshipAvailable = true,
        modality = course.modality,
        star= course.start
    )

}