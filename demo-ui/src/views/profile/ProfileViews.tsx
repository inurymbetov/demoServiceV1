import React from "react";
import {classNameBuilder} from "../../utils/className";
import {IProps} from "./IProps";

const ProfileViews: React.FC<IProps> = () => {
  const cn = classNameBuilder("profile")
  return (
    <div className={cn()}>
      profile view
    </div>
  )
}

export default ProfileViews;