import { City } from './city.model';

export interface Location {
  id: number;
  name: string;
  description: string;
  disponibilite: boolean;
  latitude: number;
  longitude: number;
  city: City;
}
