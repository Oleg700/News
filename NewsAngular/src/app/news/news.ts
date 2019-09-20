
import {Comment} from '../comment/comment';

export class News{
    id: number;
    title: string;
    date: string;
    brief: string;
    content: string;

    comments: Comment[];
}