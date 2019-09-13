import { News } from '../news/news';
import { User } from '../user/user';
import { Comment } from './comment';

export class CommentRequest{
    comment: Comment;
    username: String;
}