import { Cart } from "./Cart"
import { CartItem } from "./CartItem"

enum TypeProprietaire {
    PP = 'PP',
    PM = 'PM',
}
export class Product {
    idProduct!: number
    barcode!: number
    price!: number
    stockQuantity!: number
    description!: String
    name!: String
    category!: String
    owner!: String
    image!: String
    reorder!: boolean
    typeProprietaire!: TypeProprietaire;
    cartItems!:CartItem[]
     
}