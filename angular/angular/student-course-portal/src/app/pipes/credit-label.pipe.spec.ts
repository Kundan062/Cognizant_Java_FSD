import { CreditLabelPipe } from './credit-label.pipe';

describe('CreditLabelPipe', () => {
  const pipe = new CreditLabelPipe();

  it('transforms 1 to "1 Credit"', () => expect(pipe.transform(1)).toBe('1 Credit'));
  it('transforms 3 to "3 Credits"', () => expect(pipe.transform(3)).toBe('3 Credits'));
  it('transforms null to "No Credits"', () => expect(pipe.transform(null)).toBe('No Credits'));
  it('transforms 0 to "No Credits"', () => expect(pipe.transform(0)).toBe('No Credits'));
});
