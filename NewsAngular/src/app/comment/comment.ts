import { News } from '../news/news';
import { User } from '../user/user';

export class Comment{
    id: BigInteger;
    content: string;
    news: News;
    user: User;
}