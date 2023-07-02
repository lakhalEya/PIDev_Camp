import { Allocation } from "./Allocation"
import { Product } from "./Product"

export class Allocationitem {
    id!: number

    allocation!: Allocation
    dateDebut !: Date
    dateFin !: Date
    price !: number
    Quantity !: number
     product !:Product
}

