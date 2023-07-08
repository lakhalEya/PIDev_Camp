import { UserRole } from '../enums/user-role';
import { UserStatus } from '../enums/user-status';
import { Profile } from './Profile';

export class userModel {
    id?: number;
    userName?: string;
    email?: string;
    password?: string;
    profile?: Profile
    role?: UserRole;
    userStatus?: UserStatus;
}