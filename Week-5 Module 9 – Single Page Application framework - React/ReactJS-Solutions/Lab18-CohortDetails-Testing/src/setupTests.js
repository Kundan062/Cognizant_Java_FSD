import Enzyme from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
// Use enzyme-adapter-react-16 instead of the above if the project uses React 16

Enzyme.configure({ adapter: new Adapter() });
