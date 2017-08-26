import { Lms2Page } from './app.po';

describe('lms2 App', () => {
  let page: Lms2Page;

  beforeEach(() => {
    page = new Lms2Page();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
