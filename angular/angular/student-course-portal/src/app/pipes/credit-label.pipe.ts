import { Pipe, PipeTransform } from '@angular/core';

// Pure by default: only re-runs when the input reference changes,
// which keeps it cheap even inside frequently re-rendered lists.
@Pipe({
  name: 'creditLabel',
  standalone: true
})
export class CreditLabelPipe implements PipeTransform {
  transform(credits: number | null | undefined): string {
    if (!credits || credits <= 0) return 'No Credits';
    return credits === 1 ? '1 Credit' : `${credits} Credits`;
  }
}
