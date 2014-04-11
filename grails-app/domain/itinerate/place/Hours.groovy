package itinerate.place

class Hours {
    Integer startTime = 0
    Integer endTime = 0
    
    List exceptTimes

    static belongsTo = [event: Event]
    static hasMany = [exceptTimes: Long]
    static embedded = ['exceptTimes']
    static constraints = {
        startTime nullable: false
        endTime nullable: false
    }
}
