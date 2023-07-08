
import { Parc } from '../../parc/models/parc.model';
import { Sale } from './sale';

export class Reservation {
    id: number;
    startDate: Date;
    endDate: Date;
    personnbr: number;
    status: string;
    category: string;
    //change to User
    user:number;
    sale : Sale
    parc : Parc;
    //activity : Activity;


    

    


}
