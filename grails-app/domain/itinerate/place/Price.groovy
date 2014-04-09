package itinerate.place

class Price {
    BigDecimal adultPrice
    BigDecimal childPrice
    BigDecimal specialChildPrice
    BigDecimal studentPrice
    BigDecimal seniorPrice
    
    static belongsTo = [event: Event]
    static constraints = {
    }
}
