import { Role } from '../role/role';

export class User{
    id: number;
    username: string;
    password: string;
    enable: boolean;
    roles: Role[];
}