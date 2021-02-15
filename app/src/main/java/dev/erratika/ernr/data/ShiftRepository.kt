package dev.erratika.ernr.data

class ShiftRepository (private val shiftDao: ShiftDao){
    fun getNextShift(){
        shiftDao.getNextShift()
    }


}
