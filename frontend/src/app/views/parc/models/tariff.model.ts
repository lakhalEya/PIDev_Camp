
export interface Tariff {
  id: number;
  price: number;
  name: string;
  description: string;
  duration: number;
  discount: number;
  currency: string;
  validityStartDate: Date;
  validityEndDate: Date;
  applicableDays: Set<string>;
  applicableStartTime: string;
  applicableEndTime: string;
  minimumParticipants: number;
  maximumParticipants: number;
  additionalServices: Set<string>;
  restrictions: string;
}
