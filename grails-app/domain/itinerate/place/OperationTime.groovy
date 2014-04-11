package itinerate.place

import itinerate.plan.HolidayTime

class OperationTime
{
	String startMonth
	String endMonth

	List hours
	List holidays

	static belongsTo = [event: Event]
	static hasMany = [hours: Hours, holidays: HolidayTime]
    static constraints = {
    }
}
