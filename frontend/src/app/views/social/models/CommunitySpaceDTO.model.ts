import { PostDTO } from './PostDTO.model'
import { CommunityCategoryDTO } from './CommunityCategoryDTO.model'

export class  CommunitySpaceDTO {

    idForum: number ;
    title: string ;
    description: String ;
    category: string ;
    posts: Set<PostDTO> ;
    fileData: Blob;
    fileName: String ;
    fileType: String ;
}

