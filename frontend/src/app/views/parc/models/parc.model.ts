import { Location } from './location.model';

export interface Parc {
  idParc: number;
  name: string;
  description: string;
  maxCapacity: number;
  category: string;
  creationDate: Date;
  lastUpdateDate: Date;
  status: string;
  location: Location;
  imageURL: string;
  amenities: string[];
  rating: number;
  owner: string;
  selected: boolean;
}
