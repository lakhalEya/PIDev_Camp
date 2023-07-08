import { Tariff } from './tariff.model';
import { Parc } from './parc.model';


export interface Activity {
  id: number;
  name: string;
  location: Location;
  startDate: Date;
  endDate: Date;
  organizer: string;
  tariff: Tariff[];
  parc: Parc;
  category: string;
  creationDate: Date;
  lastModificationDate: Date;
  description: string;
  maxParticipants: number;
  minParticipants: number;
  registrationRequired: boolean;
  tags: string[];
  imageUrl: string;
  status: string;
}
