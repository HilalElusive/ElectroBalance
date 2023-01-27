import '/js/kioskboard/kioskboard-aio.js';

KioskBoard.Run('.virtual-keyboard', {
  keysArrayOfObjects: null,
  keysJsonUrl: '/js/kioskboard/kioskboard-keys-english.json',
  specialCharactersObject: null,
  language: 'en',
  theme: 'light',
  capsLockActive: false,
  allowRealKeyboard: true,
  cssAnimations: true,
  cssAnimationsDuration: 360,
  cssAnimationsStyle: 'slide',
  keysAllowSpacebar: true,
  keysSpacebarText: 'Space',
  keysFontFamily: 'sans-serif',
  keysFontSize: '22px',
  keysFontWeight: 'normal',
  keysIconSize: '25px',
  allowMobileKeyboard: false,
  autoScroll: true,
});
KioskBoard.Merge({
    theme: 'dark',
});