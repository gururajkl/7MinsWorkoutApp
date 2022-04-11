package com.example.a7minutesworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.moveworkout,
            isCompleted = false,
            isSelected = false
        )

        val wallSit = ExerciseModel(
            2,
            "Wall Sit",
            R.drawable.wallsitgiphy,
            isCompleted = false,
            isSelected = false
        )

        val sidePlank = ExerciseModel(
            3,
            "Side Plank",
            R.drawable.gifysideplank,
            isCompleted = false,
            isSelected = false
        )

        val pushUp = ExerciseModel(
            4,
            "Push up",
            R.drawable.pushupgif,
            isCompleted = false,
            isSelected = false
        )

        val lunge = ExerciseModel(
            5,
            "Lunges",
            R.drawable.lunges,
            isCompleted = false,
            isSelected = false
        )

        val dribbleChair = ExerciseModel(
            6,
            "Dribble Chair",
            R.drawable.dribbblechairdip,
            isCompleted = false,
            isSelected = false
        )

        val squat = ExerciseModel(
            7,
            "Squat",
            R.drawable.squat,
            isCompleted = false,
            isSelected = false
        )

        exerciseList.add(jumpingJacks)
        exerciseList.add(wallSit)
        exerciseList.add(sidePlank)
        exerciseList.add(pushUp)
        exerciseList.add(lunge)
        exerciseList.add(dribbleChair)
        exerciseList.add(squat)

        return exerciseList
    }
}