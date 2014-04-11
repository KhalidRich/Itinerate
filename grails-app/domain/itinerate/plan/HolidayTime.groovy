package itinerate.plan

import itinerate.place.OperationTime

class HolidayTime {
	Holiday day
	Integer closingTime

	static belongsTo = [operation: OperationTime]
    static constraints = {
    }
}
