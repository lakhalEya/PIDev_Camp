import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
})
export class FilterPipe implements PipeTransform {
  transform(value: any[], filterValue: any, propertyName: any): any[] {
    if (filterValue && filterValue !== 'all') {
      return value.filter((element) =>
        element[propertyName].toString().toLowerCase().includes(filterValue.toString().toLowerCase())
      );
    }

    return value;
  }
}