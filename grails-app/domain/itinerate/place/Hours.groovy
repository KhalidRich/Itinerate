package itinerate.place

class Hours {
    Long startTime
    Long endTime
    
    List exceptTimes

    static belongsTo = [event: Event]
    static hasMany = [exceptTimes: Long]
    static embedded = ['exceptTimes']
    static constraints = {
        startTime nullable: false
        endTime nullable: false
    }
}
