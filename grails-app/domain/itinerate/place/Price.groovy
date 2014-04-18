package itinerate.place

class Price {
    BigDecimal adultPrice = new BigDecimal(-1)
    BigDecimal childPrice = new BigDecimal(-1)
    String childRange
    BigDecimal specialChildPrice = new BigDecimal(-1)
    String specialChildRange
    BigDecimal studentPrice = new BigDecimal(-1)
    BigDecimal seniorPrice = new BigDecimal(-1)
    
    static belongsTo = [event: Event]
    static constraints = {
    }
}
