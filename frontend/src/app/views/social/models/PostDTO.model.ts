import { CommentDTO } from './CommentDTO.model'
import { Visibility } from './Visibility.model'
import { CommunityCategory } from './CommunityCategory.model'
export class  PostDTO {
  idPost: number ;
  title: String ;
  content: String ;
  category: string ;
  datePublication: Date;
  visibility: string;
  comments: Set<CommentDTO> ;
  communitySpaceId: number ;
}
