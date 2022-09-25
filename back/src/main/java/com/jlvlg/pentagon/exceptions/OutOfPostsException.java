package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;
import org.springframework.data.domain.Slice;

public class OutOfPostsException extends OutOfObjectsException {
    private Slice<Post> slice;
    public OutOfPostsException(Slice<Post> slice) {
        super("posts");
        this.slice = slice;
    }

    public Slice<Post> getSlice() {
        return slice;
    }
}
